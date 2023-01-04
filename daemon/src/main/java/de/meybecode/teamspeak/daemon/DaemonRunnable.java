package de.meybecode.teamspeak.daemon;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import de.meybecode.config.configuration.Config;
import lombok.Getter;
import de.meybecode.teamspeak.common.TeamSpeakAction;
import de.meybecode.teamspeak.daemon.config.TeamSpeakConfigGeneric;
import de.meybecode.teamspeak.daemon.config.TeamSpeakSupportConfigGeneric;
import de.meybecode.teamspeak.daemon.handler.ActionHandler;
import de.meybecode.teamspeak.daemon.handler.LogHandler;
import de.meybecode.teamspeak.daemon.handler.VerifyHandler;
import de.meybecode.teamspeak.daemon.hook.TeamSpeakHook;
import de.meybecode.teamspeak.daemon.listener.TeamSpeakSupportListener;
import de.meybecode.teamspeak.http.client.TeamSpeakHttpClient;
import de.meybecode.teamspeak.http.client.VerifyHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Getter
public class DaemonRunnable implements Runnable {

    private TS3Query query;
    private final Config config;
    private final Logger logger;
    private final LogHandler logHandler;
    private final ActionHandler actionHandler;
    private final VerifyHandler verifyHandler;
    private final VerifyHttpClient verifyHttpClient;
    private final TeamSpeakHttpClient teamSpeakHttpClient;
    private final TeamSpeakConfigGeneric teamSpeakConfigGeneric;
    private final TeamSpeakSupportConfigGeneric supportConfigGeneric;

    public DaemonRunnable() {
        this.config = new Config("config.yml");
        this.logger = LoggerFactory.getLogger(DaemonRunnable.class);
        this.teamSpeakConfigGeneric = config.getGenericType("teamspeak.config", TeamSpeakConfigGeneric.class);
        this.supportConfigGeneric = config.getGenericType("teamspeak.support", TeamSpeakSupportConfigGeneric.class);

        logger.info("establishing connection to rest server: " + teamSpeakConfigGeneric.getRestHost() + "...");
        this.verifyHttpClient = new VerifyHttpClient(teamSpeakConfigGeneric.getVerifyRestHost(), teamSpeakConfigGeneric.getVerifyApiKey());
        this.teamSpeakHttpClient = new TeamSpeakHttpClient(teamSpeakConfigGeneric.getRestHost(), teamSpeakConfigGeneric.getApiKey());
        this.logHandler = new LogHandler(teamSpeakHttpClient.getTeamSpeakLogService());
        this.actionHandler = new ActionHandler(teamSpeakHttpClient.getTeamSpeakActionService());
        this.verifyHandler = new VerifyHandler(verifyHttpClient.getUserVerifyService());
        establishTeamSpeakConnection();
        Runtime.getRuntime().addShutdownHook(new TeamSpeakHook(getQuery()));
    }

    private void establishTeamSpeakConnection() {
        final TS3Config ts3Config = new TS3Config();
        ts3Config.setHost(teamSpeakConfigGeneric.getTeamSpeakHost());

        final TS3Query query = new TS3Query(ts3Config);
        query.connect();

        final TS3Api ts3Api = query.getApi();
        ts3Api.login(teamSpeakConfigGeneric.getQueryName(), teamSpeakConfigGeneric.getQueryPassword());
        ts3Api.selectVirtualServerById(teamSpeakConfigGeneric.getVirtualServerId(), teamSpeakConfigGeneric.getQueryNickName());
        ts3Api.addClientPermission(ts3Api.whoAmI().getDatabaseId(), "i_client_needed_serverquery_view_power", 0, false);

        ts3Api.registerAllEvents();
        ts3Api.addTS3Listeners(new TeamSpeakSupportListener(ts3Api, logHandler, actionHandler, verifyHandler, teamSpeakHttpClient.getLogUserService(), supportConfigGeneric));
        //openSupport(ts3Api);
        this.query = query;
        closeSupport(ts3Api);
    }

    private void openSupport(TS3Api api) {
        api.editChannel(getSupportConfigGeneric().getSupportQueueChannelId(), ChannelProperty.CHANNEL_NAME, getSupportConfigGeneric().getSupportChannelNameWhenOpen());
        api.editChannel(getSupportConfigGeneric().getSupportQueueChannelId(), ChannelProperty.CHANNEL_PASSWORD, null);
    }

    private void closeSupport(TS3Api api) {
        api.editChannel(getSupportConfigGeneric().getSupportQueueChannelId(), ChannelProperty.CHANNEL_NAME, getSupportConfigGeneric().getSupportChannelNameWhenClosed());
        api.editChannel(getSupportConfigGeneric().getSupportQueueChannelId(), ChannelProperty.CHANNEL_PASSWORD, getSupportConfigGeneric().getSupportChannelPasswordWhenClosed());
    }

    private void createActions() {
        TeamSpeakAction teamSpeakAction = new TeamSpeakAction(0, "join_queue");
        try {
            teamSpeakHttpClient.getTeamSpeakActionService().createAction(teamSpeakAction).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        logger.info("starting teamspeak daemon...");
    }
}

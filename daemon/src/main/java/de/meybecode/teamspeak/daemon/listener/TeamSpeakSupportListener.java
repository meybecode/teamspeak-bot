package de.meybecode.teamspeak.daemon.listener;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.api.event.ClientMovedEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.wrapper.ClientInfo;
import lombok.RequiredArgsConstructor;
import de.meybecode.teamspeak.common.LogUser;
import de.meybecode.teamspeak.common.TeamSpeakLog;
import de.meybecode.teamspeak.daemon.action.Action;
import de.meybecode.teamspeak.daemon.config.TeamSpeakSupportConfigGeneric;
import de.meybecode.teamspeak.daemon.handler.ActionHandler;
import de.meybecode.teamspeak.daemon.handler.LogHandler;
import de.meybecode.teamspeak.daemon.handler.VerifyHandler;
import de.meybecode.teamspeak.http.client.service.LogUserService;

import java.io.IOException;

@RequiredArgsConstructor
public class TeamSpeakSupportListener extends TS3EventAdapter {

    private final TS3Api ts3Api;
    private final LogHandler logHandler;
    private final ActionHandler actionHandler;
    private final VerifyHandler verifyHandler;
    private final LogUserService logUserService;
    private final TeamSpeakSupportConfigGeneric supportConfigGeneric;

    @Override
    public void onClientMoved(ClientMovedEvent event) {
        if (event.getTargetChannelId() == supportConfigGeneric.getSupportQueueChannelId()) {
            ClientInfo client = ts3Api.getClientInfo(event.getClientId());
            handleLogCreation(client, Action.JOIN_QUEUE);
        }
    }

    private void handleLogCreation(ClientInfo client, Action action) {
        verifyHandler.getVerificationUser(client.getDatabaseId()).whenComplete((verification, throwable) -> {
            LogUser logUser = new LogUser((long) client.getDatabaseId(),
                    (verification == null) ? null : String.valueOf(verification.getPlayer()), client.getNickname());
            TeamSpeakLog teamSpeakLog = new TeamSpeakLog(0, actionHandler.getAction(action), logUser,
                    System.currentTimeMillis());
            try {
                logUserService.createUser(logUser).execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logHandler.pushLog(teamSpeakLog);
        });
    }
}

package de.meybecode.teamspeak.daemon.hook;

import com.github.theholywaffle.teamspeak3.TS3Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeamSpeakHook extends Thread {

    private final Logger logger;
    private final TS3Query query;

    public TeamSpeakHook(TS3Query query) {
        this.logger = LoggerFactory.getLogger(TeamSpeakHook.class);
        this.query = query;
    }

    @Override
    public void run() {
        logger.info("the teamspeak daemon is shutting down...");
        query.exit();
    }
}

package de.meybecode.teamspeak.daemon;

import org.apache.log4j.BasicConfigurator;

public class TeamSpeakDaemon {

    public static void main(String[] args) {
        BasicConfigurator.configure();
        new Thread(new DaemonRunnable()).start();
    }

}

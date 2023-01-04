package de.meybecode.teamspeak.http.server.exception;

public class TeamSpeakLogNotFoundException extends RuntimeException {

    public TeamSpeakLogNotFoundException(long id) {
        super("there is no log with the id " + id + "!");
    }

}

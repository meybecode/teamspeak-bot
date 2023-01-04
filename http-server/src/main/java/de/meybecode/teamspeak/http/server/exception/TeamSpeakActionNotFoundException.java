package de.meybecode.teamspeak.http.server.exception;

public class TeamSpeakActionNotFoundException extends RuntimeException {

    public TeamSpeakActionNotFoundException(long id) {
        super("a teamspeak action with the id " + id + " could not be found!");
    }

}

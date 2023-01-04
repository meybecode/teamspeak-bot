package de.meybecode.teamspeak.http.server.exception;

public class LogUserNotFoundException extends RuntimeException {

    public LogUserNotFoundException(long userId) {
        super("the user " + userId + " does not exist!");
    }

}

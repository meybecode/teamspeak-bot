package de.meybecode.teamspeak.daemon.action;

import lombok.Getter;

@Getter
public enum Action {

    JOIN_QUEUE(2);

    private final long id;

    Action(long id) {
        this.id = id;
    }

}

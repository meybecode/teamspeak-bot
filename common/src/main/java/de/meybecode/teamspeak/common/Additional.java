package de.meybecode.teamspeak.common;

public record Additional(long additionId, LogUser user, LogUser executor, TeamSpeakAction action) {
}

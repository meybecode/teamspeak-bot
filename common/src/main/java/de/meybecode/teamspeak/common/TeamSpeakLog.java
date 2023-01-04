package de.meybecode.teamspeak.common;

public record TeamSpeakLog(long logId, TeamSpeakAction teamSpeakAction, LogUser logUser, long createdAt) {

}

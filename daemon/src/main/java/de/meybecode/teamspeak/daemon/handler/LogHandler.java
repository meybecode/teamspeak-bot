package de.meybecode.teamspeak.daemon.handler;

import lombok.RequiredArgsConstructor;
import de.meybecode.teamspeak.common.TeamSpeakLog;
import de.meybecode.teamspeak.http.client.service.TeamSpeakLogService;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public class LogHandler {

    private final TeamSpeakLogService teamSpeakLogService;

    public void pushLog(TeamSpeakLog teamSpeakLog) {
        CompletableFuture.runAsync(() -> {
            try {
                teamSpeakLogService.createLog(teamSpeakLog).execute();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}

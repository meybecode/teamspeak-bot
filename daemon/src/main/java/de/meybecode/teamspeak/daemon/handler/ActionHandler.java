package de.meybecode.teamspeak.daemon.handler;


import de.meybecode.teamspeak.common.TeamSpeakAction;
import de.meybecode.teamspeak.daemon.action.Action;
import de.meybecode.teamspeak.http.client.service.TeamSpeakActionService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ActionHandler {

    private final Map<Long, TeamSpeakAction> actions;

    public ActionHandler(TeamSpeakActionService actionService) {
        this.actions = new HashMap<>();
        CompletableFuture.runAsync(() -> {
            try {
                Objects.requireNonNull(actionService.getAllActions().execute().body()).forEach(teamSpeakAction ->
                        actions.put(teamSpeakAction.actionId(), teamSpeakAction));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public TeamSpeakAction getAction(Action action) {
        return actions.get(action.getId());
    }

}

package de.meybecode.teamspeak.http.server.controller.implementation;

import de.meybecode.teamspeak.common.TeamSpeakAction;
import de.meybecode.teamspeak.http.server.controller.TeamSpeakActionController;
import de.meybecode.teamspeak.http.server.entity.TeamSpeakActionEntity;
import de.meybecode.teamspeak.http.server.exception.TeamSpeakActionNotFoundException;
import de.meybecode.teamspeak.http.server.repository.TeamSpeakActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamSpeakActionControllerImplementation implements TeamSpeakActionController {

    @Autowired
    private TeamSpeakActionRepository actionRepository;

    @Override
    public TeamSpeakActionEntity getAction(long id) {
        return actionRepository.findById(id).orElseThrow(() -> new TeamSpeakActionNotFoundException(id));
    }

    @Override
    public TeamSpeakActionEntity createAction(TeamSpeakAction teamSpeakAction) {
        TeamSpeakActionEntity teamSpeakActionEntity = new TeamSpeakActionEntity();
        teamSpeakActionEntity.setActionId(teamSpeakAction.actionId());
        teamSpeakActionEntity.setDisplayName(teamSpeakAction.displayName());
        return actionRepository.save(teamSpeakActionEntity);
    }

    @Override
    public List<TeamSpeakActionEntity> getAllActions() {
        return actionRepository.findAll();
    }
}

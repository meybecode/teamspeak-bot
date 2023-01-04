package de.meybecode.teamspeak.http.server.controller.implementation;

import de.meybecode.teamspeak.common.TeamSpeakLog;
import de.meybecode.teamspeak.http.server.controller.LogUserController;
import de.meybecode.teamspeak.http.server.controller.TeamSpeakActionController;
import de.meybecode.teamspeak.http.server.controller.TeamSpeakLogController;
import de.meybecode.teamspeak.http.server.entity.TeamSpeakLogEntity;
import de.meybecode.teamspeak.http.server.exception.TeamSpeakLogNotFoundException;
import de.meybecode.teamspeak.http.server.repository.TeamSpeakLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamSpeakLogControllerImplementation implements TeamSpeakLogController {

    @Autowired
    private TeamSpeakLogRepository logRepository;
    @Autowired
    private TeamSpeakActionController actionController;
    @Autowired
    private LogUserController logUserController;

    @Override
    public TeamSpeakLogEntity getTeamSpeakLog(long id) {
        return logRepository.findById(id).orElseThrow(() -> new TeamSpeakLogNotFoundException(id));
    }

    @Override
    public TeamSpeakLogEntity createTeamSpeakLog(TeamSpeakLog teamSpeakLog) {
        TeamSpeakLogEntity teamSpeakLogEntity = new TeamSpeakLogEntity();
        teamSpeakLogEntity.setLogId(teamSpeakLog.logId());
        teamSpeakLogEntity.setTeamSpeakAction(actionController.getAction(teamSpeakLog.teamSpeakAction().actionId()));
        teamSpeakLogEntity.setLogUser(logUserController.getLogUser(teamSpeakLog.logUser().userId()));
        teamSpeakLogEntity.setCreatedAt(teamSpeakLog.createdAt());
        return logRepository.save(teamSpeakLogEntity);
    }
}

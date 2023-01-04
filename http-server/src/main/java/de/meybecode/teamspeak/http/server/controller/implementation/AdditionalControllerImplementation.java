package de.meybecode.teamspeak.http.server.controller.implementation;

import de.meybecode.teamspeak.common.Additional;
import de.meybecode.teamspeak.http.server.controller.AdditionalController;
import de.meybecode.teamspeak.http.server.controller.LogUserController;
import de.meybecode.teamspeak.http.server.controller.TeamSpeakActionController;
import de.meybecode.teamspeak.http.server.entity.AdditionalEntity;
import de.meybecode.teamspeak.http.server.repository.AdditionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AdditionalControllerImplementation implements AdditionalController {

    @Autowired
    private AdditionalRepository additionalRepository;
    @Autowired
    private LogUserController logUserController;
    @Autowired
    private TeamSpeakActionController actionController;

    @Override
    public AdditionalEntity getAdditional(long id) {
        return additionalRepository.findById(id).orElse(null);
    }

    @Override
    public AdditionalEntity getAdditionalByUserId(long userId) {
        return getAllAdditions().stream().filter(filter -> filter.getUser().getUserId().equals(userId)).findFirst().orElse(null);
    }

    @Override
    public AdditionalEntity createAdditional(Additional additional) {
        AdditionalEntity additionalEntity = new AdditionalEntity();
        additionalEntity.setAdditionalId(additional.additionId());
        additionalEntity.setUser(logUserController.getLogUser(additional.user().userId()));
        additionalEntity.setExecutor(logUserController.getLogUser(additional.executor().userId()));
        additionalEntity.setTeamSpeakAction(actionController.getAction(additional.action().actionId()));
        return additionalRepository.save(additionalEntity);
    }

    @Override
    public List<AdditionalEntity> getAllAdditions() {
        return additionalRepository.findAll();
    }
}

package de.meybecode.teamspeak.http.server.controller.implementation;

import de.meybecode.teamspeak.common.LogUser;
import de.meybecode.teamspeak.http.server.controller.LogUserController;
import de.meybecode.teamspeak.http.server.entity.LogUserEntity;
import de.meybecode.teamspeak.http.server.exception.LogUserNotFoundException;
import de.meybecode.teamspeak.http.server.repository.LogUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogUserControllerImplementation implements LogUserController {

    @Autowired
    private LogUserRepository logUserRepository;

    @Override
    public LogUserEntity getLogUser(long id) {
        return logUserRepository.findById(id).orElseThrow(() -> new LogUserNotFoundException(id));
    }

    @Override
    public LogUserEntity createLogUser(LogUser logUser) {
        LogUserEntity logUserEntity = new LogUserEntity();
        logUserEntity.setUserId(logUser.userId());
        logUserEntity.setUserName(logUser.userName());
        logUserEntity.setUniqueId(logUser.uniqueId());
        return logUserRepository.save(logUserEntity);
    }
}

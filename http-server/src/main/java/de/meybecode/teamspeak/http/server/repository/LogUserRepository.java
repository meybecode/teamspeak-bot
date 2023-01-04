package de.meybecode.teamspeak.http.server.repository;

import de.meybecode.teamspeak.http.server.entity.LogUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogUserRepository extends JpaRepository<LogUserEntity, Long> {
}

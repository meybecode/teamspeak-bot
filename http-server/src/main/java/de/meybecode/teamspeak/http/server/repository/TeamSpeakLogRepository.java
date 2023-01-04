package de.meybecode.teamspeak.http.server.repository;

import de.meybecode.teamspeak.http.server.entity.TeamSpeakLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamSpeakLogRepository extends JpaRepository<TeamSpeakLogEntity, Long> {
}

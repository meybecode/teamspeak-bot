package de.meybecode.teamspeak.http.server.repository;

import de.meybecode.teamspeak.http.server.entity.TeamSpeakActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamSpeakActionRepository extends JpaRepository<TeamSpeakActionEntity, Long> {
}

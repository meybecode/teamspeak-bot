package de.meybecode.teamspeak.http.server.repository;

import de.meybecode.teamspeak.http.server.entity.AdditionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalRepository extends JpaRepository<AdditionalEntity, Long> {
}

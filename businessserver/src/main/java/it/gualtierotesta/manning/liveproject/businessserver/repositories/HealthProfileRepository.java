package it.gualtierotesta.manning.liveproject.businessserver.repositories;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HealthProfileRepository extends JpaRepository<HealthProfile, Integer> {

    Optional<HealthProfile> findHealthProfileByUsername(String username);
}

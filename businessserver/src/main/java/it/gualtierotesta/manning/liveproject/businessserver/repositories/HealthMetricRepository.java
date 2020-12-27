package it.gualtierotesta.manning.liveproject.businessserver.repositories;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthMetric;
import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HealthMetricRepository extends JpaRepository<HealthMetric, Integer> {

    @Query("SELECT h FROM HealthMetric h WHERE h.profile.username=:username")
    List<HealthMetric> findHealthMetricHistory(String username);

    @Query("DELETE FROM HealthMetric h WHERE h.profile=:profile")
    @Modifying
    void deleteAllForUser(HealthProfile profile);
}

package it.gualtierotesta.manning.liveproject.businessserver.services;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthMetric;
import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthProfile;
import it.gualtierotesta.manning.liveproject.businessserver.exceptions.NonExistentHealthProfileException;
import it.gualtierotesta.manning.liveproject.businessserver.repositories.HealthMetricRepository;
import it.gualtierotesta.manning.liveproject.businessserver.repositories.HealthProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HealthMetricService {

    private final HealthMetricRepository healthMetricRepository;
    private final HealthProfileRepository healthProfileRepository;

    public HealthMetricService(HealthMetricRepository healthMetricRepository, HealthProfileRepository healthProfileRepository) {
        this.healthMetricRepository = healthMetricRepository;
        this.healthProfileRepository = healthProfileRepository;
    }

    public void addHealthMetric(HealthMetric healthMetric) {
        Optional<HealthProfile> profile = healthProfileRepository.findHealthProfileByUsername(healthMetric.getProfile().getUsername());

        profile.ifPresentOrElse(
                p ->
                {
                    healthMetric.setProfile(p);
                    healthMetricRepository.save(healthMetric);
                },
                () -> {
                    throw new NonExistentHealthProfileException("The profile doesn't exist");
                });

    }

    public List<HealthMetric> findHealthMetricHistory(String username) {
        return healthMetricRepository.findHealthMetricHistory(username);
    }

    public void deleteHealthMetricForUser(String username) {
        Optional<HealthProfile> profile = healthProfileRepository.findHealthProfileByUsername(username);

        profile.ifPresentOrElse(healthMetricRepository::deleteAllForUser,
                () -> {
                    throw new NonExistentHealthProfileException("The profile doesn't exist");
                }
        );
    }
}

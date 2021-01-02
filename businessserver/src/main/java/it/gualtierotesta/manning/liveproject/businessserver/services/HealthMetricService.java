package it.gualtierotesta.manning.liveproject.businessserver.services;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthMetric;
import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthProfile;
import it.gualtierotesta.manning.liveproject.businessserver.exceptions.NonExistentHealthProfileException;
import it.gualtierotesta.manning.liveproject.businessserver.repositories.HealthMetricRepository;
import it.gualtierotesta.manning.liveproject.businessserver.repositories.HealthProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class HealthMetricService {

    private final HealthMetricRepository healthMetricRepository;
    private final HealthProfileRepository healthProfileRepository;

    @PreAuthorize("#healthMetric.profile.username == authentication.principal.claims['user_name']")
    public void addHealthMetric(final HealthMetric healthMetric) {
        Optional<HealthProfile> profile =
                healthProfileRepository.findHealthProfileByUsername(healthMetric.getProfile().getUsername());

        profile.ifPresentOrElse(
                p -> {
                    healthMetric.setProfile(p);
                    healthMetricRepository.save(healthMetric);
                },
                () -> {
                    throw new NonExistentHealthProfileException("The profile doesn't exist");
                });

    }

    @PreAuthorize("#username == authentication.principal.claims['user_name']")
    public List<HealthMetric> findHealthMetricHistory(final String username) {
        return healthMetricRepository.findHealthMetricHistory(username);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteHealthMetricForUser(final String username) {
        Optional<HealthProfile> profile = healthProfileRepository.findHealthProfileByUsername(username);

        profile.ifPresentOrElse(healthMetricRepository::deleteAllForUser,
                () -> {
                    throw new NonExistentHealthProfileException("The profile doesn't exist");
                }
        );
    }
}

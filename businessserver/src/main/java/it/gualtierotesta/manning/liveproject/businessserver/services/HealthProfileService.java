package it.gualtierotesta.manning.liveproject.businessserver.services;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthProfile;
import it.gualtierotesta.manning.liveproject.businessserver.exceptions.HealthProfileAlreadyExistsException;
import it.gualtierotesta.manning.liveproject.businessserver.exceptions.NonExistentHealthProfileException;
import it.gualtierotesta.manning.liveproject.businessserver.repositories.HealthProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class HealthProfileService {

    private final HealthProfileRepository healthProfileRepository;

    public HealthProfileService(HealthProfileRepository healthProfileRepository) {
        this.healthProfileRepository = healthProfileRepository;
    }

    public void addHealthProfile(HealthProfile profile) {
        Optional<HealthProfile> healthProfile = healthProfileRepository.findHealthProfileByUsername(profile.getUsername());

        if (healthProfile.isEmpty()) {
            healthProfileRepository.save(profile);
        } else {
            throw new HealthProfileAlreadyExistsException("This health profile already exists.");
        }
    }

    public HealthProfile findHealthProfile(String username) {
        Optional<HealthProfile> healthProfile =
                healthProfileRepository.findHealthProfileByUsername(username);

        return healthProfile
                .orElseThrow(() -> new NonExistentHealthProfileException("No profile found for the provided username."));
    }

    public void deleteHealthProfile(String username) {
        Optional<HealthProfile> healthProfile =
                healthProfileRepository.findHealthProfileByUsername(username);

        healthProfile.ifPresentOrElse(
                p -> healthProfileRepository.delete(p),
                () -> {
                    throw new NonExistentHealthProfileException("No profile found for the provided username.");
                });
    }
}

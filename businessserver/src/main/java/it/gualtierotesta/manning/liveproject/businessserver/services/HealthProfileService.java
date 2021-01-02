package it.gualtierotesta.manning.liveproject.businessserver.services;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthProfile;
import it.gualtierotesta.manning.liveproject.businessserver.exceptions.HealthProfileAlreadyExistsException;
import it.gualtierotesta.manning.liveproject.businessserver.exceptions.NonExistentHealthProfileException;
import it.gualtierotesta.manning.liveproject.businessserver.repositories.HealthProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class HealthProfileService {

    private final HealthProfileRepository healthProfileRepository;

    @PreAuthorize("#profile.username == authentication.principal.claims['user_name']")
    public void addHealthProfile(final HealthProfile profile) {
        Optional<HealthProfile> healthProfile =
                healthProfileRepository.findHealthProfileByUsername(profile.getUsername());
        if (healthProfile.isEmpty()) {
            healthProfileRepository.save(profile);
        } else {
            throw new HealthProfileAlreadyExistsException("This health profile already exists.");
        }
    }

    @PostAuthorize("hasRole('ADMIN') or returnObject.username == authentication.principal.claims['user_name']")
    public HealthProfile findHealthProfile(final String username) {

        return healthProfileRepository.findHealthProfileByUsername(username)
                .orElseThrow(() ->
                        new NonExistentHealthProfileException("No profile found for the provided username."));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteHealthProfile(final String username) {
        Optional<HealthProfile> healthProfile =
                healthProfileRepository.findHealthProfileByUsername(username);

        healthProfile.ifPresentOrElse(
                p -> healthProfileRepository.delete(p),
                () -> {
                    throw new NonExistentHealthProfileException("No profile found for the provided username.");
                });
    }
}

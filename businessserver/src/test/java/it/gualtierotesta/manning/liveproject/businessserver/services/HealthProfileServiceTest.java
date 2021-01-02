package it.gualtierotesta.manning.liveproject.businessserver.services;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthProfile;
import it.gualtierotesta.manning.liveproject.businessserver.repositories.HealthProfileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
class HealthProfileServiceTest {

    @Autowired
    private HealthProfileService service;

    @MockBean
    private HealthProfileRepository repository;

    private static final String BOB = "Bob";
    private static final String ALICE = "Alice";

    @Test
    void setup() {
        assertNotNull(service);
    }

    @Test
    @WithCustomUser(username = BOB, roles = "USER")
    @DisplayName("User should be able to add his own profile")
    void test1() {
        // given
        HealthProfile healthProfile = createProfile(BOB);
        // when
        service.addHealthProfile(healthProfile);
        // then
        verify(repository).save(healthProfile);
    }

    @Test
    @WithCustomUser(username = ALICE, roles = "USER")
    @DisplayName("User cannot add a profile for another user")
    void test2() {
        // given
        HealthProfile healthProfile = createProfile(BOB);
        // when + then
        assertThrows(org.springframework.security.access.AccessDeniedException.class,
            () -> service.addHealthProfile(healthProfile));
        verify(repository, never()).save(healthProfile);
    }

    @Test
    @WithCustomUser(username = BOB, roles = "USER")
    @DisplayName("User cannot add an already existing profile")
    void test3() {
        // given
        HealthProfile healthProfile = createProfile(BOB);
        given(repository.findHealthProfileByUsername(BOB))
            .willReturn(Optional.of(healthProfile));
        // when + then
        assertThrows(it.gualtierotesta.manning.liveproject.businessserver.exceptions.HealthProfileAlreadyExistsException.class,
            () -> service.addHealthProfile(healthProfile));
        verify(repository, never()).save(healthProfile);
    }


    private static HealthProfile createProfile(final String pUserName) {
        HealthProfile healthProfile = new HealthProfile();
        healthProfile.setUsername(pUserName);
        return healthProfile;
    }
}

package it.gualtierotesta.manning.liveproject.businessserver.controllers;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthProfile;
import it.gualtierotesta.manning.liveproject.businessserver.services.HealthProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
@Slf4j
@RequiredArgsConstructor
public class HealthProfileController {

    private final HealthProfileService healthProfileService;

    @PostMapping
    public void addHealthProfile(@RequestBody final HealthProfile healthProfile) {
        healthProfileService.addHealthProfile(healthProfile);
    }

    @GetMapping("/{username}")
    public HealthProfile findHealthProfile(
        @AuthenticationPrincipal Principal pPrincipal,
        @PathVariable final String username) {

        return healthProfileService.findHealthProfile(username);
    }

    @DeleteMapping("/{username}")
    // TODO re-enable after adding security
    // public void deleteHealthProfile(@PathVariable String username, Authentication a) {
    public void deleteHealthProfile(@PathVariable final String username) {
        healthProfileService.deleteHealthProfile(username);
    }
}

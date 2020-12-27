package it.gualtierotesta.manning.liveproject.businessserver.controllers;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthProfile;
import it.gualtierotesta.manning.liveproject.businessserver.services.HealthProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
@Slf4j
public class HealthProfileController {

    private final HealthProfileService healthProfileService;

    public HealthProfileController(final HealthProfileService healthProfileService) {
        this.healthProfileService = healthProfileService;
    }

    @PostMapping
    public void addHealthProfile(@RequestBody final HealthProfile healthProfile) {
        healthProfileService.addHealthProfile(healthProfile);
    }

    @GetMapping("/{username}")
    public HealthProfile findHealthProfile(@PathVariable final String username) {
        log.info("get profile for [{}]", username);
        return healthProfileService.findHealthProfile(username);
    }

    @DeleteMapping("/{username}")
    // TODO re-enable after adding security
    // public void deleteHealthProfile(@PathVariable String username, Authentication a) {
    public void deleteHealthProfile(@PathVariable final String username) {
        healthProfileService.deleteHealthProfile(username);
    }
}

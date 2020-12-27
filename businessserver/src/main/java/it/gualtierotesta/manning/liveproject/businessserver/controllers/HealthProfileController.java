package it.gualtierotesta.manning.liveproject.businessserver.controllers;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthProfile;
import it.gualtierotesta.manning.liveproject.businessserver.services.HealthProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class HealthProfileController {

    private final HealthProfileService healthProfileService;

    public HealthProfileController(HealthProfileService healthProfileService) {
        this.healthProfileService = healthProfileService;
    }

    @PostMapping
    public void addHealthProfile(@RequestBody HealthProfile healthProfile) {
        healthProfileService.addHealthProfile(healthProfile);
    }

    @GetMapping("/{username}")
    public HealthProfile findHealthProfile(@PathVariable String username) {
        return healthProfileService.findHealthProfile(username);
    }

    @DeleteMapping("/{username}")
    // TODO re-enable after adding security
    // public void deleteHealthProfile(@PathVariable String username, Authentication a) {
    public void deleteHealthProfile(@PathVariable String username) {
        healthProfileService.deleteHealthProfile(username);
    }
}

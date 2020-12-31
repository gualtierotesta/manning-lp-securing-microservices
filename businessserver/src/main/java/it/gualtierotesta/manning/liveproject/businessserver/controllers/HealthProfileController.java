package it.gualtierotesta.manning.liveproject.businessserver.controllers;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthProfile;
import it.gualtierotesta.manning.liveproject.businessserver.services.HealthProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public HealthProfile findHealthProfile(
            @AuthenticationPrincipal Principal pPrincipal,
            @PathVariable final String username) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) pPrincipal;
        log.info("get profile for [{}]:", username);
        System.out.println("token.getName() = " + token.getName());
        System.out.println("token = " + token);
        System.out.println("token.getToken() = " + token.getToken());
        System.out.println("token.getDetails() = " + token.getDetails());
        return healthProfileService.findHealthProfile(username);
    }

    @DeleteMapping("/{username}")
    // TODO re-enable after adding security
    // public void deleteHealthProfile(@PathVariable String username, Authentication a) {
    public void deleteHealthProfile(@PathVariable final String username) {
        healthProfileService.deleteHealthProfile(username);
    }
}

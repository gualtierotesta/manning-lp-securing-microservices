package it.gualtierotesta.manning.liveproject.businessserver.controllers;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthMetric;
import it.gualtierotesta.manning.liveproject.businessserver.services.HealthMetricService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metric")
public class HealthMetricController {

    private final HealthMetricService healthMetricService;

    public HealthMetricController(HealthMetricService healthMetricService) {
        this.healthMetricService = healthMetricService;
    }

    @PostMapping
    public void addHealthMetric(@RequestBody HealthMetric healthMetric) {
        healthMetricService.addHealthMetric(healthMetric);
    }

    @GetMapping("/{username}")
    public List<HealthMetric> findHealthMetrics(@PathVariable String username) {
        return healthMetricService.findHealthMetricHistory(username);
    }

    @DeleteMapping("/{username}")
    public void deleteHealthMetricForUser(@PathVariable String username) {
        healthMetricService.deleteHealthMetricForUser(username);
    }
}

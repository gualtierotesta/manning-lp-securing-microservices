package it.gualtierotesta.manning.liveproject.businessserver.controllers;

import it.gualtierotesta.manning.liveproject.businessserver.entities.HealthMetric;
import it.gualtierotesta.manning.liveproject.businessserver.services.HealthMetricService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/metric")
@Slf4j
@RequiredArgsConstructor
public class HealthMetricController {

    private final HealthMetricService healthMetricService;

    @PostMapping
    public void addHealthMetric(@RequestBody final HealthMetric healthMetric) {
        healthMetricService.addHealthMetric(healthMetric);
    }

    @GetMapping("/{username}")
    public List<HealthMetric> findHealthMetrics(@PathVariable final String username) {
        return healthMetricService.findHealthMetricHistory(username);
    }

    @DeleteMapping("/{username}")
    public void deleteHealthMetricForUser(@PathVariable final String username) {
        healthMetricService.deleteHealthMetricForUser(username);
    }
}

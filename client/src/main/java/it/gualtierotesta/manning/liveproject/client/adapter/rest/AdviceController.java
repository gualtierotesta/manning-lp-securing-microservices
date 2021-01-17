package it.gualtierotesta.manning.liveproject.client.adapter.rest;

import it.gualtierotesta.manning.liveproject.client.application.port.in.HealthAdvicePort;
import it.gualtierotesta.manning.liveproject.client.domain.MetricValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@Slf4j
public class AdviceController {

    private final HealthAdvicePort healthAdvice;

    @PostMapping("/data")
    public void addMetricValues(@RequestBody final List<MetricValue> pMetricValues) {
        log.info("{}", pMetricValues);
        healthAdvice.createHealthAdvices(pMetricValues);
    }
}

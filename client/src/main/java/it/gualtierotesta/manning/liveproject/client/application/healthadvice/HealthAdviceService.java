package it.gualtierotesta.manning.liveproject.client.application.healthadvice;

import it.gualtierotesta.manning.liveproject.client.application.port.in.HealthAdvicePort;
import it.gualtierotesta.manning.liveproject.client.application.port.out.ResourceServerPort;
import it.gualtierotesta.manning.liveproject.client.domain.HealthAdvice;
import it.gualtierotesta.manning.liveproject.client.domain.MetricValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class HealthAdviceService implements HealthAdvicePort {

    private final ResourceServerPort resourceServer;

    @Override
    public void createHealthAdvices(final List<MetricValue> pMetricValues) {
        log.info("{}", pMetricValues);
        pMetricValues.stream()
            .map(HealthAdviceService::createDummyAdvice)
            .forEach(resourceServer::sendHealthAdvice);

    }

    private static HealthAdvice createDummyAdvice(final MetricValue pMetricValue) {
        return HealthAdvice.builder()
            .username(pMetricValue.getUsername())
            .advice(pMetricValue.getValue() > 10.0d ? "Be careful" : "OK")
            .build();
    }
}

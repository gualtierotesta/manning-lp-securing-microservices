package it.gualtierotesta.manning.liveproject.client.application.port.in;

import it.gualtierotesta.manning.liveproject.client.domain.MetricValue;

import java.util.List;

/**
 * Health advice interface
 */
public interface HealthAdvicePort {

    void createHealthAdvices(List<MetricValue> pMetricValues);
}

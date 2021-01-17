package it.gualtierotesta.manning.liveproject.client.domain;

import lombok.Data;

/**
 * User health metric value
 */
@Data
public class MetricValue {

    private String username;
    private HealthMetricType healthMetricType;
    private double value;

}

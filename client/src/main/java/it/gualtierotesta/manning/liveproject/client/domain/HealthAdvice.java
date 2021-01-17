package it.gualtierotesta.manning.liveproject.client.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class HealthAdvice {

    String advice;
    String username;

}

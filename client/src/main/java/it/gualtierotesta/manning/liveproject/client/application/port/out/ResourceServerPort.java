package it.gualtierotesta.manning.liveproject.client.application.port.out;

import it.gualtierotesta.manning.liveproject.client.domain.HealthAdvice;

public interface ResourceServerPort {

    void sendHealthAdvice(HealthAdvice pHealthAdvice);
}

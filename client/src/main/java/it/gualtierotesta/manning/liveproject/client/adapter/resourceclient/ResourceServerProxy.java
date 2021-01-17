package it.gualtierotesta.manning.liveproject.client.adapter.resourceclient;

import it.gualtierotesta.manning.liveproject.client.application.config.TokenManager;
import it.gualtierotesta.manning.liveproject.client.application.port.out.ResourceServerPort;
import it.gualtierotesta.manning.liveproject.client.domain.HealthAdvice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@Slf4j
@RequiredArgsConstructor
public class ResourceServerProxy implements ResourceServerPort {

    @Value("${app.resource.server.url}")
    private String resourceServerURL;

    private final TokenManager tokenManager;
    private final RestTemplate restTemplate;

    @Override
    public void sendHealthAdvice(final HealthAdvice pHealthAdvice) {
        log.info("{}", pHealthAdvice);
        String token = tokenManager.getAccessToken();
        String url = resourceServerURL + "/advice";
        log.debug("url={} token={}", url, token);
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, "Bearer " + token);
        HttpEntity<List<HealthAdvice>> request = new HttpEntity<>(List.of(pHealthAdvice), headers);

        restTemplate.postForObject(url, request, Void.class);
    }

}

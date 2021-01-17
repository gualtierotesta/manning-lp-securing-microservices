package it.gualtierotesta.manning.liveproject.client.application.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TokenManager {

    @Value("${app.security.client.registration.name}")
    private String clientRegistrationName;

    @Value("${spring.security.oauth2.client.registration.app.client-id}")
    private String clientId;

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public String getAccessToken() {
        OAuth2AuthorizeRequest authorizeRequest =
            OAuth2AuthorizeRequest
                .withClientRegistrationId(clientRegistrationName)
                .principal(clientId)
                .build();
        OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);
        OAuth2AccessToken accessToken = authorizedClient.getAccessToken();

        String tokenValue = accessToken.getTokenValue();
        log.info("token={}", tokenValue);
        return tokenValue;
    }
}

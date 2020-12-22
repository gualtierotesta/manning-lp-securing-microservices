package it.gualtierotesta.manning.liveproject.authserver.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

/**
 * Client (domain object)
 */
@Value
@Builder
public class Client {

    Long id;
    String clientId;
    @With
    String secret;
    Integer accessTokenValiditySeconds;
    Integer refreshTokenValiditySeconds;

    String scopes;       // CSV
    String redirectUris;  // CSV
    String grantTypes;   // CSV
    String resourceIds;  // CSV
    String authorities;  // CSV
}

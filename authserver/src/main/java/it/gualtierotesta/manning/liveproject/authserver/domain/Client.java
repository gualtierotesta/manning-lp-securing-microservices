package it.gualtierotesta.manning.liveproject.authserver.domain;

import lombok.Builder;
import lombok.Value;

/**
 * Client (domain object)
 */
@Value
@Builder
public class Client {

    Long id;
    String clientId;
    String secret;
    String grantTypes;
    String scopes;

}

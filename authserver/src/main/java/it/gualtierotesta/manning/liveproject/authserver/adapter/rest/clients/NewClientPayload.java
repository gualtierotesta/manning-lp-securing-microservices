package it.gualtierotesta.manning.liveproject.authserver.adapter.rest.clients;

import it.gualtierotesta.manning.liveproject.authserver.domain.Client;
import lombok.Data;

@Data
class NewClientPayload {

    private String clientId;
    private String secret;
    Integer accessTokenValiditySeconds;
    Integer refreshTokenValiditySeconds;
    private String scopes;
    private String redirectUris;
    private String grantTypes;
    private String resourceIds;
    private String authorities;

    public Client map() {
        return Client.builder()
            .clientId(clientId)
            .secret(secret)
            .grantTypes(grantTypes)
            .scopes(scopes)
            .build();
    }
}

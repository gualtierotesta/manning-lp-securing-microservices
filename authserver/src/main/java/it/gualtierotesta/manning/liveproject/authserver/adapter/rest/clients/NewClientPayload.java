package it.gualtierotesta.manning.liveproject.authserver.adapter.rest.clients;

import it.gualtierotesta.manning.liveproject.authserver.domain.Client;
import lombok.Data;

@Data
class NewClientPayload {

    private String clientId;
    private String secret;
    private String grantTypes;
    private String scopes;

    public Client map() {
        return Client.builder()
            .clientId(clientId)
            .secret(secret)
            .grantTypes(grantTypes)
            .scopes(scopes)
            .build();
    }
}

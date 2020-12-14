package it.gualtierotesta.manning.liveproject.authserver.adapter.storage.clients;

import it.gualtierotesta.manning.liveproject.authserver.domain.Client;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "client_id", unique = true, nullable = false)
    private String clientId;

    @Column(nullable = false)
    private String secret;

    @Column(name = "grant_types", nullable = false)
    private String grantTypes;

    @Column(nullable = false)
    private String scopes;

    static ClientEntity fromDomain(final Client pClient) {
        ClientEntity entity = new ClientEntity();
        entity.id = pClient.getId();
        entity.clientId = pClient.getClientId();
        entity.secret = pClient.getSecret();
        entity.grantTypes = pClient.getGrantTypes();
        entity.scopes = pClient.getScopes();
        return entity;
    }

    Client toDomain() {
        return Client.builder()
            .id(id)
            .clientId(clientId)
            .secret(secret)
            .grantTypes(grantTypes)
            .scopes(scopes)
            .build();
    }
}

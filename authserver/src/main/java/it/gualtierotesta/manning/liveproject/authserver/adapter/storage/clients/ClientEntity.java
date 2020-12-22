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

    @Column(name = "access_token_validity")
    Integer accessTokenValiditySeconds;

    @Column(name = "refresh_token_validity")
    Integer refreshTokenValiditySeconds;

    @Column
    private String scopes;

    @Column(name = "redirect_uris")
    String redirectUris;

    @Column(name = "grant_types", nullable = false)
    private String grantTypes;

    @Column(name = "resource_ids")
    String resourceIds;

    @Column
    String authorities;  // CSV

    static ClientEntity fromDomain(final Client pClient) {
        ClientEntity entity = new ClientEntity();
        entity.id = pClient.getId();
        entity.clientId = pClient.getClientId();
        entity.secret = pClient.getSecret();
        entity.accessTokenValiditySeconds = pClient.getAccessTokenValiditySeconds();
        entity.refreshTokenValiditySeconds = pClient.getRefreshTokenValiditySeconds();
        entity.scopes = pClient.getScopes();
        entity.redirectUris = pClient.getRedirectUris();
        entity.grantTypes = pClient.getGrantTypes();
        entity.resourceIds = pClient.getResourceIds();
        entity.authorities = pClient.getAuthorities();
        return entity;
    }

    Client toDomain() {
        return Client.builder()
            .id(id)
            .clientId(clientId)
            .secret(secret)
            .accessTokenValiditySeconds(accessTokenValiditySeconds)
            .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
            .scopes(scopes)
            .redirectUris(redirectUris)
            .grantTypes(grantTypes)
            .resourceIds(resourceIds)
            .authorities(authorities)
            .build();
    }
}

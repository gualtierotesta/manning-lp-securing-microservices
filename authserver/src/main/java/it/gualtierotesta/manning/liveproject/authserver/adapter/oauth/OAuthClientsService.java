package it.gualtierotesta.manning.liveproject.authserver.adapter.oauth;

import it.gualtierotesta.manning.liveproject.authserver.application.port.out.ClientsStoragePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Custom implementation of the OAuth ClientDetailsService
 * <p>
 * See https://stackoverflow.com/questions/31798631/stackoverflowerror-in-spring-oauth2-with-custom-clientdetailsservice
 */
@Service
@Primary
@Slf4j
@RequiredArgsConstructor
public class OAuthClientsService implements ClientDetailsService {

    private final ClientsStoragePort storage;

    @Override
    public ClientDetails loadClientByClientId(final String clientId) throws ClientRegistrationException {
        log.debug("clientId={}", clientId);
        return storage.findByClientId(clientId)
            .map(OAuthClientsService::mapTo)
            .orElseThrow(() ->
                new ClientRegistrationException(
                    String.format("Oauth client not found: clientId=%s", clientId)));

    }

    private static ClientDetails mapTo(final Client pClient) {
        log.debug("Client = {}", pClient);
        BaseClientDetails details = new BaseClientDetails();
        details.setClientId(pClient.getClientId());
        details.setClientSecret(pClient.getSecret());
        details.setAuthorizedGrantTypes(convertToSet(pClient.getGrantTypes()));
        details.setScope(convertToSet(pClient.getScopes()));
        details.setRegisteredRedirectUri(convertToSet(pClient.getRedirectUris()));
        details.setResourceIds(convertToSet(pClient.getResourceIds()));
        details.setAuthorities(convertToSet(pClient.getAuthorities())
            .stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toUnmodifiableSet()));
        log.debug("details={}", details);
        return details;
    }

    private static Set<String> convertToSet(final String pCSVString) {
        return pCSVString != null ?
            Arrays.stream(pCSVString.split(",")).map(String::trim).collect(Collectors.toUnmodifiableSet()) :
            Collections.emptySet();
    }
}

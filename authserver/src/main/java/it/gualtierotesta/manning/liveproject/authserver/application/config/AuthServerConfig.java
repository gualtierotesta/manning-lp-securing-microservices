package it.gualtierotesta.manning.liveproject.authserver.application.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

/**
 * OAuth authorization server configuration
 *
 * @author gualtiero.testa
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${app.security.key.password}")
    private String keyPassword;

    @Value("${app.security.key.privateFile}")
    private String keyPrivateFile;

    @Value("${app.security.key.alias}")
    private String keyAlias;

    private final AuthenticationManager authenticationManager;

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
            .authenticationManager(authenticationManager)
            .tokenStore(tokenStore())
            .accessTokenConverter(jwtAccessTokenConverter());
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        var converter = new JwtAccessTokenConverter();
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(
            new ClassPathResource(keyPrivateFile), keyPassword.toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair(keyAlias));
        return converter;
    }

    /**
     * OAuth clients registration
     *
     * @param pConfigurer Client details configurer
     * @throws Exception if it fails
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer pConfigurer) throws Exception {

        pConfigurer.inMemory()

            // Password grant client
            .withClient("client-p")
            .secret("$2y$12$4o2Lc32RR2G6rOrAMcsQr.ydvE/gT.H1ngHYir95.RwOs9bp/dsMW")
            .authorizedGrantTypes("password", "refresh_token")
            .scopes("read")
            .and()

            // Authorization code client
            .withClient("client-ac")
//            .secret("$2y$12$4o2Lc32RR2G6rOrAMcsQr.ydvE/gT.H1ngHYir95.RwOs9bp/dsMW")
            .authorizedGrantTypes("authorization_code", "refresh_token")
            .scopes("read")
            .redirectUris("http://localhost:8080/home")
            .and()

            // Client credentials client
            .withClient("client-c")
            .secret("$2y$12$4o2Lc32RR2G6rOrAMcsQr.ydvE/gT.H1ngHYir95.RwOs9bp/dsMW")
            .authorizedGrantTypes("client_credentials")
            .scopes("info");

    }

}

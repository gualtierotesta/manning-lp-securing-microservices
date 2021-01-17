package it.gualtierotesta.manning.liveproject.client.application.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;

/**
 * Security configuration
 */
@Configuration
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(final HttpSecurity http) throws Exception {

        // Enabled OAUTH client
        http.oauth2Client();

        // Disable CSRF
        http.csrf(
            c -> c.ignoringAntMatchers("/api/**")
        );

        // All endpoints are unauthenticated
        http.authorizeRequests().anyRequest().permitAll();
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
        final ClientRegistrationRepository clientRegistrationRepository,
        final OAuth2AuthorizedClientRepository authorizedClientRepository) {

        OAuth2AuthorizedClientProvider authorizedClientProvider =
            OAuth2AuthorizedClientProviderBuilder.builder()
                .clientCredentials()
                .build();

        var authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(
            clientRegistrationRepository,
            authorizedClientRepository);

        authorizedClientManager
            .setAuthorizedClientProvider(authorizedClientProvider);

        return authorizedClientManager;
    }
}

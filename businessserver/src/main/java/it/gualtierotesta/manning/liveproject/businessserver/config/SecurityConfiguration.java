package it.gualtierotesta.manning.liveproject.businessserver.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${app.security.publickey}")
    private String publicKey;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()

                // All DELETE operations require ADMIN role
                .mvcMatchers(HttpMethod.DELETE).hasRole("ADMIN")

                // All operations require USER or ADMIN role
                .anyRequest().hasAnyRole("ADMIN", "USER")

                .and()
                .oauth2ResourceServer(c -> c.jwt(j -> {
                    j.decoder(jwtDecoder());
                    j.jwtAuthenticationConverter(jwtAuthenticationConverter());
                }));

    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

    // Used to validate the JWT tokens via the public key
    private JwtDecoder jwtDecoder() {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            var key = Base64.getDecoder().decode(publicKey);
            var x509 = new X509EncodedKeySpec(key);
            var rsaKey = (RSAPublicKey) keyFactory.generatePublic(x509);
            return NimbusJwtDecoder.withPublicKey(rsaKey).build();
        } catch (Exception e) {
            throw new RuntimeException("Wrong public key");
        }
    }

    // Used to extract authorities from the JWT token and convert to Spring GrantedAuthorities list
    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            log.debug("jwt = {}", jwt);
            List<String> authorities = (List<String>) jwt.getClaims().get("authorities");
            return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        });
        return converter;
    }

}

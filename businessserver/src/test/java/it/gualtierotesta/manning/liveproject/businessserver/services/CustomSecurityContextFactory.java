package it.gualtierotesta.manning.liveproject.businessserver.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class CustomSecurityContextFactory implements WithSecurityContextFactory<WithCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithCustomUser pAnnotation) {

        Map<String, Object> headers = Map.of("user_name", pAnnotation.username());
        Map<String, Object> claims = Map.of("user_name", pAnnotation.username());
        Instant issuesAt = Instant.now();
        Instant expiresAt = issuesAt.plusSeconds(1000);

        List<GrantedAuthority> authorities = Arrays.asList(pAnnotation.roles())
            .stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
            .collect(Collectors.toList());

        Jwt jwt = new Jwt("token", issuesAt, expiresAt, headers, claims);
        Authentication a = new JwtAuthenticationToken(jwt, authorities);
        a.setAuthenticated(true);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(a);
        return context;
    }
}

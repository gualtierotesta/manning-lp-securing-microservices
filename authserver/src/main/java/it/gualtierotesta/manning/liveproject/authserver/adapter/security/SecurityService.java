package it.gualtierotesta.manning.liveproject.authserver.adapter.security;

import it.gualtierotesta.manning.liveproject.authserver.application.port.in.UsersServicePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Spring UserDetailsService custom implementation. Users data are retrieved from the storage
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityService implements UserDetailsService {

    private final UsersServicePort service;

    @Override
    public UserDetails loadUserByUsername(final String pUsername) throws UsernameNotFoundException {
        log.debug("username={}", pUsername);
        return service.findByUsername(pUsername)
            .map(SecurityUser::new)
            .orElseThrow(() -> new UsernameNotFoundException("No user with username=" + pUsername));
    }
}

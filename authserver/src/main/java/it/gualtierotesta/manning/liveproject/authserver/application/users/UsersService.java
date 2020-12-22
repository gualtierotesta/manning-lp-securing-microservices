package it.gualtierotesta.manning.liveproject.authserver.application.users;

import it.gualtierotesta.manning.liveproject.authserver.application.port.in.UsersServicePort;
import it.gualtierotesta.manning.liveproject.authserver.application.port.out.UsersStoragePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UsersService implements UsersServicePort {

    private final UsersStoragePort storage;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Collection<User> listAll() {
        return storage.listAll();
    }

    @Override
    public User createNewUser(final User pUser) {
        String encryptedPassword = passwordEncoder.encode(pUser.getPassword());
        return storage.create(pUser.withPassword(encryptedPassword));
    }

    @Override
    public Optional<User> findByUsername(final String pUsername) {
        return storage.findByUsername(pUsername);
    }
}

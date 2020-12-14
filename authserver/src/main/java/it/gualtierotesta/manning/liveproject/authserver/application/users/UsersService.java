package it.gualtierotesta.manning.liveproject.authserver.application.users;

import it.gualtierotesta.manning.liveproject.authserver.application.port.in.UsersServicePort;
import it.gualtierotesta.manning.liveproject.authserver.application.port.out.UsersStoragePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
class UsersService implements UsersServicePort {

    private final UsersStoragePort storage;

    @Override
    public Collection<User> listAll() {
        return storage.listAll();
    }

    @Override
    public User createNewUser(final User pUser) {
        return storage.create(pUser);
    }

    @Override
    public Optional<User> findByUsername(final String pUsername) {
        return storage.findByUsername(pUsername);
    }
}

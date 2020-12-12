package it.gualtierotesta.manning.liveproject.authserver.application.users;

import it.gualtierotesta.manning.liveproject.authserver.application.port.in.UsersServicePort;
import it.gualtierotesta.manning.liveproject.authserver.application.port.out.UsersStoragePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
class UsersService implements UsersServicePort {

    private final UsersStoragePort usersStorage;

    @Override
    public Collection<AppUser> listAll() {
        return usersStorage.listAll();
    }
}

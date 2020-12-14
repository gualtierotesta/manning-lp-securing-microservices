package it.gualtierotesta.manning.liveproject.authserver.adapter.storage.users;

import it.gualtierotesta.manning.liveproject.authserver.application.port.out.UsersStoragePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersStorageService implements UsersStoragePort {

    private final UsersRepository repository;

    @Override
    public Collection<User> listAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
            .map(e -> e.toDomain())
            .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public User create(final User pUser) {
        UserEntity ent = UserEntity.fromDomain(pUser);
        return repository.save(ent).toDomain();
    }

    @Override
    public Optional<User> findByUsername(final String pUsername) {
        return repository.findByUsername(pUsername)
            .map(UserEntity::toDomain);
    }

}

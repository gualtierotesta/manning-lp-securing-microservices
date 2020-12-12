package it.gualtierotesta.manning.liveproject.authserver.adapter.storage;

import it.gualtierotesta.manning.liveproject.authserver.application.port.out.UsersStoragePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.AppUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class UsersStorageService implements UsersStoragePort {

    private final UsersRepository repository;

    @Override
    public Collection<AppUser> listAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
            .map(this::mapper)
            .collect(Collectors.toUnmodifiableList());
    }

    private AppUser mapper(UserEntity pUserEntity) {
        return AppUser.builder()
            .userId(pUserEntity.getId())
            .username(pUserEntity.getUsername())
            .password(pUserEntity.getPassword())
            .authority(pUserEntity.getAuthority())
            .build();
    }
}

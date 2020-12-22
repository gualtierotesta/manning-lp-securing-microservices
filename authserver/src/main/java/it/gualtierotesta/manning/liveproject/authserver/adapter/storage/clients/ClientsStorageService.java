package it.gualtierotesta.manning.liveproject.authserver.adapter.storage.clients;

import it.gualtierotesta.manning.liveproject.authserver.application.port.out.ClientsStoragePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.Client;
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
public class ClientsStorageService implements ClientsStoragePort {

    private final ClientsRepository repository;

    @Override
    public Collection<Client> listAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false)
            .map(e -> e.toDomain())
            .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Client create(final Client pClient) {
        ClientEntity ent = ClientEntity.fromDomain(pClient);
        return repository.save(ent).toDomain();
    }

    @Override
    public Optional<Client> findByClientId(final String pClientId) {
        return repository.findByClientId(pClientId)
            .map(ClientEntity::toDomain);
    }
}

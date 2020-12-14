package it.gualtierotesta.manning.liveproject.authserver.application.clients;

import it.gualtierotesta.manning.liveproject.authserver.application.port.in.ClientsServicePort;
import it.gualtierotesta.manning.liveproject.authserver.application.port.out.ClientsStoragePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientsService implements ClientsServicePort {

    private final ClientsStoragePort storage;

    @Override
    public Collection<Client> listAll() {
        return storage.listAll();
    }

    @Override
    public Client createNewClient(final Client pClient) {
        return storage.create(pClient);
    }
}

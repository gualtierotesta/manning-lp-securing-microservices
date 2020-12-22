package it.gualtierotesta.manning.liveproject.authserver.application.port.out;

import it.gualtierotesta.manning.liveproject.authserver.domain.Client;

import java.util.Collection;
import java.util.Optional;

public interface ClientsStoragePort {

    Collection<Client> listAll();

    Client create(Client pClient);

    Optional<Client> findByClientId(String pClientId);
}

package it.gualtierotesta.manning.liveproject.authserver.application.port.out;

import it.gualtierotesta.manning.liveproject.authserver.domain.Client;

import java.util.Collection;

public interface ClientsStoragePort {

    Collection<Client> listAll();

    Client create(Client pClient);
}

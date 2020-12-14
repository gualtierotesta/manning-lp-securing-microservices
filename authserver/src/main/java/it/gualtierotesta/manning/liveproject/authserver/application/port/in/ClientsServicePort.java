package it.gualtierotesta.manning.liveproject.authserver.application.port.in;

import it.gualtierotesta.manning.liveproject.authserver.domain.Client;

import java.util.Collection;

public interface ClientsServicePort {

    Collection<Client> listAll();

    Client createNewClient(Client pClient);
}

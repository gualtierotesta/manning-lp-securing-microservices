package it.gualtierotesta.manning.liveproject.authserver.adapter.storage.clients;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Clients repository
 */
interface ClientsRepository extends CrudRepository<ClientEntity, Long> {

    Optional<ClientEntity> findByClientId(String pClientId);
}

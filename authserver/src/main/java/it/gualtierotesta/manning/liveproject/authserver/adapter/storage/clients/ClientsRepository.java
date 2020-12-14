package it.gualtierotesta.manning.liveproject.authserver.adapter.storage.clients;

import org.springframework.data.repository.CrudRepository;

/**
 * Clients repository
 */
interface ClientsRepository extends CrudRepository<ClientEntity, Long> {
}

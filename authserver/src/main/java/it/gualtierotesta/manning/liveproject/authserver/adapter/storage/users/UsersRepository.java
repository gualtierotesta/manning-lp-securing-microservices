package it.gualtierotesta.manning.liveproject.authserver.adapter.storage.users;

import org.springframework.data.repository.CrudRepository;

/**
 * Users repository
 */
interface UsersRepository extends CrudRepository<UserEntity, Long> {
}

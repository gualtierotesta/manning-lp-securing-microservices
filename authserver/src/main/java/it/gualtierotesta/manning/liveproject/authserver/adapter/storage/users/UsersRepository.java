package it.gualtierotesta.manning.liveproject.authserver.adapter.storage.users;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Users repository
 */
interface UsersRepository extends CrudRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String pUsername);
}

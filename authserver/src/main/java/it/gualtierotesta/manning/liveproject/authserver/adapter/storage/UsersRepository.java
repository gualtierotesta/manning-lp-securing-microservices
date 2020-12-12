package it.gualtierotesta.manning.liveproject.authserver.adapter.storage;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
}

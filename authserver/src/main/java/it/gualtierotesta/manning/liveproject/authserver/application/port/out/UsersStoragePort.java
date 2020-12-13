package it.gualtierotesta.manning.liveproject.authserver.application.port.out;

import it.gualtierotesta.manning.liveproject.authserver.domain.User;

import java.util.Collection;

public interface UsersStoragePort {

    Collection<User> listAll();

    User create(User pUser);
}

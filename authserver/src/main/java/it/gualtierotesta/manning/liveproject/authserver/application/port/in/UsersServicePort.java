package it.gualtierotesta.manning.liveproject.authserver.application.port.in;

import it.gualtierotesta.manning.liveproject.authserver.domain.User;

import java.util.Collection;

public interface UsersServicePort {

    Collection<User> listAll();

    User createNewUser(User pUser);
}

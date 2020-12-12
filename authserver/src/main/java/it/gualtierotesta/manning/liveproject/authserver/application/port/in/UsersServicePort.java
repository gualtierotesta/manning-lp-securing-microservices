package it.gualtierotesta.manning.liveproject.authserver.application.port.in;

import it.gualtierotesta.manning.liveproject.authserver.domain.AppUser;

import java.util.Collection;

public interface UsersServicePort {

    Collection<AppUser> listAll();
}

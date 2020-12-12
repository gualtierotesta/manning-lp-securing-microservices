package it.gualtierotesta.manning.liveproject.authserver.application.port.out;

import it.gualtierotesta.manning.liveproject.authserver.domain.AppUser;

import java.util.Collection;

public interface UsersStoragePort {

    Collection<AppUser> listAll();
}

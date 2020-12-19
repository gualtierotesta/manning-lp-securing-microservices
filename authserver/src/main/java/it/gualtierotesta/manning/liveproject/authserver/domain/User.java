package it.gualtierotesta.manning.liveproject.authserver.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

/**
 * User (domain object)
 */
@Value
@Builder
public class User {

    Long userId;
    String username;
    String authority;
    @With
    String password;

}

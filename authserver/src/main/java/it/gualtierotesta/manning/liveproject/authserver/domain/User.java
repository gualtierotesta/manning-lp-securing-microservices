package it.gualtierotesta.manning.liveproject.authserver.domain;

import lombok.Builder;
import lombok.Value;

/**
 * User (domain object)
 */
@Value
@Builder
public class User {

    Long userId;
    String username;
    String password;
    String authority;

}

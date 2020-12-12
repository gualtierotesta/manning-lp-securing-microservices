package it.gualtierotesta.manning.liveproject.authserver.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AppUser {

    Long userId;
    String username;
    String password;
    String authority;

}

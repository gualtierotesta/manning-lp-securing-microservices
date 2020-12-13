package it.gualtierotesta.manning.liveproject.authserver.adapter.rest.users;

import it.gualtierotesta.manning.liveproject.authserver.domain.User;
import lombok.Data;

@Data
class NewUserPayload {

    private String username;
    private String password;
    private String authority;

    User map() {
        return User.builder()
            .username(username)
            .password(password)
            .authority(authority)
            .build()  ;
    }
}

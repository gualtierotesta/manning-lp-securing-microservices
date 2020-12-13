package it.gualtierotesta.manning.liveproject.authserver.adapter.storage;

import it.gualtierotesta.manning.liveproject.authserver.domain.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String authority;

    static UserEntity from(final User pUser) {
        UserEntity entity = new UserEntity();
        entity.username = pUser.getUsername();
        entity.password =pUser.getPassword();
        entity.authority=pUser.getAuthority();
        return entity;
    }

     User map() {
        return User.builder()
            .userId(id)
            .username(username)
            .password(password)
            .authority(authority)
            .build();
    }
}

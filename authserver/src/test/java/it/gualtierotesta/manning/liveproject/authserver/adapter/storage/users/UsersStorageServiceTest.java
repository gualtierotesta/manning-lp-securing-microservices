package it.gualtierotesta.manning.liveproject.authserver.adapter.storage.users;

import it.gualtierotesta.manning.liveproject.authserver.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UsersStorageServiceTest {

    @Autowired
    private UsersRepository repository;

    private UsersStorageService sut;

    @BeforeEach
    void beforeEach() {
        sut = new UsersStorageService(repository);
    }

    @Test
    void test1() {
        Collection<User> clients = sut.listAll();
        Assertions.assertThat(clients).hasSize(1);
    }
}

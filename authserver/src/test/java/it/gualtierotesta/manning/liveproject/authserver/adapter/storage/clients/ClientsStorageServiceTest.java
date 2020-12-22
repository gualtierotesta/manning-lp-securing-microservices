package it.gualtierotesta.manning.liveproject.authserver.adapter.storage.clients;

import it.gualtierotesta.manning.liveproject.authserver.domain.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ClientsStorageServiceTest {

    @Autowired
    private ClientsRepository repository;

    private ClientsStorageService sut;

    @BeforeEach
    void beforeEach() {
        sut = new ClientsStorageService(repository);
    }

    @Test
    void test1() {
        Collection<Client> clients = sut.listAll();
        assertThat(clients).hasSize(3);
    }
}

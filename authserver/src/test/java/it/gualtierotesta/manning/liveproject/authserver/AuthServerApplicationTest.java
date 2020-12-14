package it.gualtierotesta.manning.liveproject.authserver;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

@SpringBootTest(classes = AuthServerApplication.class)
@Profile("test")
class AuthServerApplicationTest {

    @Test
    void contextLoads() {
        // Empty
    }

}

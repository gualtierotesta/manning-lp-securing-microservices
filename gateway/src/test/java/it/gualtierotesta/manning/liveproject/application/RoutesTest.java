package it.gualtierotesta.manning.liveproject.application;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.mockJwt;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {"app.businessserver.uri=http://locahost:${wiremock.server.port}"})
@AutoConfigureWireMock(port = 0)
@AutoConfigureWebTestClient
class RoutesTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    @DisplayName("setup")
    void test1() {
        assertThat(webClient).isNotNull();
    }

    @Test
    @DisplayName("Unauthenticated route profiles get unauthenticated")
    void test2() {
        webClient
            .get()
            .uri("/profile/user123")
            .exchange()
            .expectStatus().isUnauthorized();
    }

    @Test
    @DisplayName("Unauthenticated route profiles get unauthenticated")
    void test3() {

        String url = "/profile/user123";
        stubFor(WireMock.get(WireMock.urlEqualTo(url))
            .willReturn(WireMock.aResponse().withStatus(200)));

        webClient
            .mutateWith(mockJwt())
            .get()
            .uri(url)
            .exchange()
            .expectStatus().isOk();
    }
}

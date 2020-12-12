package it.gualtierotesta.manning.liveproject.authserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClientCredentialsGrantTest {

    @Autowired
    private MockMvc mvc;

    private static final String CLIENT_ID = "client-c";
    private static final String CLIENT_SECRET = "secret";
    private static final String URL = "/oauth/token";

    @Test
    @DisplayName("Valid call should return access token")
    void test1() throws Exception {
        mvc.perform(post(URL)
            .queryParam("grant_type", "client_credentials")
            .queryParam("scope", "info")
            .with(httpBasic(CLIENT_ID, CLIENT_SECRET)))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(jsonPath("$.access_token").isNotEmpty())
            .andExpect(jsonPath("$.refresh_token").doesNotExist())
            .andExpect(status().isOk());
    }
}

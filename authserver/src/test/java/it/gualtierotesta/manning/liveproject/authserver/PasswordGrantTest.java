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
class PasswordGrantTest {

    @Autowired
    private MockMvc mvc;

    private static final String CLIENT_ID = "client-p";
    private static final String CLIENT_SECRET = "secret";
    private static final String URL = "/oauth/token";

    @Test
    @DisplayName("without client credentials should return Unauthenticated")
    void test1() throws Exception {
        mvc.perform(post(URL)
            .queryParam("grant_type", "password")
            .queryParam("username", "john")
            .queryParam("password", "12345")
            .queryParam("scope", "read"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Valid call should return access and refresh tokens")
    void test2() throws Exception {
        mvc.perform(post(URL)
            .queryParam("grant_type", "password")
            .queryParam("username", "john")
            .queryParam("password", "12345")
            .queryParam("scope", "read")
            .with(httpBasic(CLIENT_ID, CLIENT_SECRET)))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(jsonPath("$.access_token").isNotEmpty())
            .andExpect(jsonPath("$.refresh_token").exists())
            .andExpect(status().isOk());
    }
}

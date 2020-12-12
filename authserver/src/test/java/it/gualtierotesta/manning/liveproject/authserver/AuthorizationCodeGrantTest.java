package it.gualtierotesta.manning.liveproject.authserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorizationCodeGrantTest {

    @Autowired
    private MockMvc mvc;

    private static final String CLIENT_ID = "client-ac";
    //    private static final String CLIENT_SECRET = "secret";
    private static final String URL = "/oauth/authorize";

    @Test
    @DisplayName("Valid call should redirect to login page")
    void test1() throws Exception {
        mvc.perform(get(URL)
            .queryParam("response_type", "code")
            .queryParam("client_id", CLIENT_ID)
            .queryParam("scope", "read"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(header().exists("Location"))
            .andExpect(status().is3xxRedirection());
    }
}

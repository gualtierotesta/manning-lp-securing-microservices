package it.gualtierotesta.manning.liveproject.authserver.adapter.rest.clients;

import it.gualtierotesta.manning.liveproject.authserver.application.port.in.ClientsServicePort;
import it.gualtierotesta.manning.liveproject.authserver.domain.Client;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClientsController.class)
class ClientsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientsServicePort service;

    @Test
    @DisplayName("List all clients")
    void test1() throws Exception {
        // given
        given(service.listAll())
            .willReturn(List.of(Client.builder().clientId("my-client").build()));
        // when + then
        mockMvc.perform(get("/api/clients")
            .accept(MediaType.APPLICATION_JSON)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$[0].clientId").isNotEmpty());
    }
}

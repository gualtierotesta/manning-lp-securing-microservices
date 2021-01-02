package it.gualtierotesta.manning.liveproject.businessserver.controllers;

import it.gualtierotesta.manning.liveproject.businessserver.services.HealthMetricService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HealthMetricControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HealthMetricService service;

    @Test
    @DisplayName("Authenticated user can list his metrics")
    @WithMockUser(username = "bob")
    void test1() throws Exception {
        String url = "/metric/{username}";
        mvc.perform(get(url, "bob"))
            .andExpect(status().isOk());
    }


}

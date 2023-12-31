package de.neuefische.backend.securedController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class SecuredControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DirtiesContext
    @WithMockUser
    void secured() throws Exception {
        mockMvc.perform(get("/api/secured"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from secured endpoint"));
    }

    @Test
    @DirtiesContext
    void secured_notLoggedIn() throws Exception {
        mockMvc.perform(get("/api/secured"))
                .andExpect(status().isUnauthorized());
    }
}
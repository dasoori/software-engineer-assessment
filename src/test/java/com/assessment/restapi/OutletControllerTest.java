package com.assessment.restapi;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = OutletController.class)
class OutletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    // We mock the service and focus on controller + HTTP layer
    @MockBean
    private OutletService service;

    @Test
    @DisplayName("GET /outlets/top-rated returns JSON list with pagination params")
    void topRatedEndpoint_ok() throws Exception {
        List<Outlet> mock = List.of(
                new Outlet(7, "Dosa Corner", 4.9),
                new Outlet(1, "Saravana Bhavan", 4.9),
                new Outlet(3, "Annapurna", 4.8)
        );
        Mockito.when(service.getTopRated(0, 3, 4.7)).thenReturn(mock);

        mockMvc.perform(get("/outlets/top-rated")
                        .param("page", "0")
                        .param("size", "3")
                        .param("minRating", "4.7"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(7))
                .andExpect(jsonPath("$[0].name").value("Dosa Corner"))
                .andExpect(jsonPath("$[0].rating").value(4.9));
    }
}

package com.stock.exercise3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StockController.class)
public class StockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockService stockService;

    @Test
    public void shouldReturnListOfBooks() throws Exception {
        when(stockService.findAll()).thenReturn(Arrays.asList(
                new Stock(1, "NB", 342.00, LocalDateTime.now(), LocalDateTime.now()),
                new Stock(2, "MTN", 90.00, LocalDateTime.now(), LocalDateTime.now())));
        mockMvc.perform(get("/stocks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].name",
                        containsInAnyOrder("NB", "MTN")));
    }

    @Test
    public void shouldReturn404WhenBookNotFound() throws Exception {
        when(stockService.find(anyInt())).thenReturn(Optional.empty());
        mockMvc.perform(get("/stocks/1")).andExpect(status().isNotFound());
    }


    @Test
    public void shouldReturnBookWhenFound() throws Exception {
        when(stockService.find(anyInt())).thenReturn(
                Optional.of(
                        new Stock(1, "NB", 342.00, LocalDateTime.now(), LocalDateTime.now())));
        mockMvc.perform(get("/stocks/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("NB")));
    }
}

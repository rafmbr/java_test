package com.rafmbr.StockQuoteManager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.rafmbr.StockQuoteManager.entities.Quote;
import com.rafmbr.StockQuoteManager.repository.QuoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class StockControllerTest {
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private QuoteRepository quoteRepository;

    @InjectMocks
    private StockController stockController;

    @BeforeEach
    void setup(){
        final StandaloneMockMvcBuilder builder = MockMvcBuilders.standaloneSetup(stockController);
        mockMvc = builder.build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void shouldGetAllQuotes() throws Exception{
        // given
        //List<Quote> listOfQuotes = new ArrayList<>();
        Map<String, Integer> quotes = new HashMap<>();
        quotes.put("2019-01-01", 10);
        quotes.put("2019-01-02", 11);
        quotes.put("2019-01-03", 14);
        Quote quote1 = Quote.builder()
                .quote("petr3")
                .quotes(quotes)
                .build();

        Mockito.doReturn(new ArrayList<>(Collections.singletonList(quote1))).when(quoteRepository).findAll();

        final MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/quotes").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        final CollectionType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, Quote.class);
        String json = result.getResponse().getContentAsString();

        Assertions.assertEquals(json, Collections.singletonList(quote1));
    }

}

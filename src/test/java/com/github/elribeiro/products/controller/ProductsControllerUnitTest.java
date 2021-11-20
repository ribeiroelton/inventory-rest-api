package com.github.elribeiro.products.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.elribeiro.products.dto.ProductDtoInput;
import com.github.elribeiro.products.dto.ProductDtoOutput;
import com.github.elribeiro.products.exception.TechnicalException;
import com.github.elribeiro.products.service.ProductsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("unit")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class ProductsControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductsService productsService;

    @BeforeEach
    public void setup() throws TechnicalException {
        ProductDtoOutput output = ProductDtoOutput.builder()
                .id(1)
                .name("Samsung N8")
                .build();

        Mockito.when(productsService.saveProduct(Mockito.any(ProductDtoInput.class))).thenReturn(output);
    }

    @Test
    public void shouldSaveAndReturnAProductWithId() throws Exception {
        ProductDtoInput input = ProductDtoInput.builder()
                .name("Samsung N8")
                .brandId(1)
                .supplierId(1)
                .unitPrice(BigDecimal.TEN)
                .description("Description")
                .photoUrl("https://photo")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/products")
                .content(new ObjectMapper().writeValueAsString(input))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn();
    }
}

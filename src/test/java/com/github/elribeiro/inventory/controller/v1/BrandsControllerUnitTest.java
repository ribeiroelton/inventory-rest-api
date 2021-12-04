package com.github.elribeiro.inventory.controller.v1;


import com.github.elribeiro.inventory.dto.BrandDtoInput;
import com.github.elribeiro.inventory.dto.BrandDtoOutput;
import com.github.elribeiro.inventory.exception.TechnicalException;
import com.github.elribeiro.inventory.service.BrandsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("unit")
@Tag("unitTest")
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = BrandsController.class)
@AutoConfigureTestDatabase
public class BrandsControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BrandsService brandsService;

    @BeforeEach
    public void setup() throws TechnicalException {
        BrandDtoOutput output = BrandDtoOutput.builder()
                .name("Samsung")
                .id(1)
                .build();

        Mockito.when(brandsService.saveBrand(Mockito.any(BrandDtoInput.class))).thenReturn(output);
    }

    @Test
    public void shouldCreateAndReturnABrandWithId() throws Exception {
        BrandDtoInput input = BrandDtoInput.builder()
                .name("Samsung")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/brands")
                .content(new ObjectMapper().writeValueAsString(input))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn();
    }

    @Test
    public void shouldNotCreateABrandWithNonExistentName() throws Exception {
        BrandDtoInput input = BrandDtoInput.builder()
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/brands")
                        .content(new ObjectMapper().writeValueAsString(input))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}

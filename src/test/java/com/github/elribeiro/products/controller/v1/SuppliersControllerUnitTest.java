package com.github.elribeiro.products.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.elribeiro.products.dto.SupplierDtoInput;
import com.github.elribeiro.products.dto.SupplierDtoOutput;
import com.github.elribeiro.products.exception.TechnicalException;
import com.github.elribeiro.products.service.SuppliersService;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("unit")
@Tag("unitTest")
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = SuppliersController.class)
@AutoConfigureTestDatabase
public class SuppliersControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SuppliersService suppliersService;

    @BeforeEach
    public void setup() throws TechnicalException {
        SupplierDtoOutput output = SupplierDtoOutput.builder()
                .id(1)
                .name("Samsung Vendor")
                .build();

        Mockito.when(suppliersService.saveSupplier(Mockito.any(SupplierDtoInput.class))).thenReturn(output);
    }

    @Test
    public void shouldCreateAndReturnASupplierWithId() throws Exception {
        SupplierDtoInput input = SupplierDtoInput.builder()
                .ie("123456789123")
                .name("Samsung Vendor")
                .cnpj("12345678000122")
                .build();

        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/suppliers")
                .content(new ObjectMapper().writeValueAsString(input))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andReturn();
    }
}

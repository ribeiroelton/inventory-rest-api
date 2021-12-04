package com.github.elribeiro.inventory.controller.v1;

import com.github.elribeiro.inventory.dto.ProductDtoInput;
import com.github.elribeiro.inventory.dto.ProductDtoOutput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

@ActiveProfiles("integration")
@Tag("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class ProductsControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate ;

    @LocalServerPort
    private Integer port;

    @Test
    @Sql("/products-controller.sql")
    public void shouldSaveAndReturnAProductWithId() {
        ProductDtoInput input = ProductDtoInput.builder()
                .name("Samsung N8")
                .brandId(1)
                .supplierId(1)
                .unitPrice(BigDecimal.TEN)
                .description("Description")
                .photoUrl("https://photo")
                .build();



        String serverUrl = "http://localhost:"+port+"/v1/products";

        ResponseEntity<ProductDtoOutput> output = testRestTemplate.postForEntity(serverUrl,input, ProductDtoOutput.class);

        Assertions.assertTrue(output.getStatusCode().is2xxSuccessful());
        Assertions.assertSame(output.getBody().getId(), 1);
    }
}

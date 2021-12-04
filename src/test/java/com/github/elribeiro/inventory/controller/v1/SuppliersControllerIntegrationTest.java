package com.github.elribeiro.inventory.controller.v1;

import com.github.elribeiro.inventory.dto.SupplierDtoInput;
import com.github.elribeiro.inventory.dto.SupplierDtoOutput;
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


@ActiveProfiles("integration")
@Tag("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
public class SuppliersControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate ;

    @LocalServerPort
    private Integer port;

    @Test
    public void shouldCreateAndReturnASupplierWithId() throws Exception {
        SupplierDtoInput input = SupplierDtoInput.builder()
                .ie("123456789123")
                .name("Samsung Vendor")
                .cnpj("12345678000122")
                .build();

        String serverUrl = "http://localhost:"+port+"/v1/suppliers";

        ResponseEntity<SupplierDtoOutput> output = testRestTemplate.postForEntity(serverUrl, input, SupplierDtoOutput.class);

        Assertions.assertTrue(output.getStatusCode().is2xxSuccessful());
        Assertions.assertSame(output.getBody().getId(), 1);
    }
}

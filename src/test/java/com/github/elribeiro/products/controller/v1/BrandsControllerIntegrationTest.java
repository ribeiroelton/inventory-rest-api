package com.github.elribeiro.products.controller.v1;


import com.github.elribeiro.products.dto.BrandDtoInput;
import com.github.elribeiro.products.dto.BrandDtoOutput;
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
public class BrandsControllerIntegrationTest {

    @Autowired
    private TestRestTemplate testRestTemplate ;

    @LocalServerPort
    private Integer port;

    @Test
    public void shouldCreateAndReturnABrandWithId() {
        BrandDtoInput input = BrandDtoInput.builder()
                .name("Samsung")
                .build();

        String serverUrl = "http://localhost:"+port+"/v1/brands";

        ResponseEntity<BrandDtoOutput> output = testRestTemplate.postForEntity(serverUrl, input, BrandDtoOutput.class);

        Assertions.assertTrue(output.getStatusCode().is2xxSuccessful());
        Assertions.assertSame(output.getBody().getId(), 1);
    }

    @Test
    public void shouldNotCreateABrandWithNonExistentName() {
        BrandDtoInput input = BrandDtoInput.builder()
                .build();

        String serverUrl = "http://localhost:"+port+"/v1/brands";

        ResponseEntity<?> output = testRestTemplate.postForEntity(serverUrl, input, BrandDtoOutput.class);

        Assertions.assertTrue(output.getStatusCode().is4xxClientError());

    }
}

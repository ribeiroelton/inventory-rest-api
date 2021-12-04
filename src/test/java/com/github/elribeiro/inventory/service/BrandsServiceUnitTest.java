package com.github.elribeiro.inventory.service;

import com.github.elribeiro.inventory.dto.BrandDtoInput;
import com.github.elribeiro.inventory.dto.BrandDtoOutput;
import com.github.elribeiro.inventory.exception.TechnicalException;
import com.github.elribeiro.inventory.model.Brand;
import com.github.elribeiro.inventory.repository.BrandRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;


@ActiveProfiles("unit")
@Tag("unitTest")
@ExtendWith(MockitoExtension.class)
public class BrandsServiceUnitTest {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandsService brandsService;

    @BeforeEach
    public void setup() {
        Brand brand = Brand.builder()
                .id(1)
                .name("Samsung")
                .build();

        when(brandRepository.save(Mockito.any(Brand.class))).thenReturn(brand);
    }

    @Test
    public void shouldCreateAndReturnABrand() throws TechnicalException {
        BrandDtoInput input = BrandDtoInput.builder().name("Samsung").build();

        BrandDtoOutput output = brandsService.saveBrand(input);

        Assertions.assertSame(input.getName(), output.getName());
        Assertions.assertEquals(output.getId(), 1);

        Mockito.verify(brandRepository, times(1)).save(Mockito.any(Brand.class));
    }
}

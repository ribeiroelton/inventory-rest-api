package com.github.elribeiro.products.repository;

import com.github.elribeiro.products.model.Brand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("unit")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BrandRepositoryUnitTest {

    @Autowired
    BrandRepository brandRepository;

    @Test
    public void shouldCreateAndPersistABrand(){
        Brand brand = Brand.builder().name("Samsung").build();

        Brand savedBrand = brandRepository.save(brand);

        Assertions.assertSame(savedBrand.getName(), brand.getName());

    }

}

package com.github.elribeiro.inventory.repository;

import com.github.elribeiro.inventory.model.Brand;
import com.github.elribeiro.inventory.model.Product;
import com.github.elribeiro.inventory.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

@DataJpaTest
@ActiveProfiles("unit")
@Tag("unitTest")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ProductRepositoryUnitTest {

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

    @Test
    public void shouldCreateAndPersistAProduct (){
        Brand brand = Brand.builder()
                .name("Samsung")
                .build();

        Supplier supplier = Supplier.builder()
                .name("Revenda Samsung")
                .cnpj("12345678000122")
                .ie("123456789123")
                .build();

        Product product = Product.builder()
                .name("Samsung J8")
                .description("Cellphone")
                .photoUrl("http://photos.service/photos?product_id=1")
                .brand(brandRepository.save(brand))
                .unitPrice(BigDecimal.valueOf(11.11))
                .supplier(supplierRepository.save(supplier))
                .build();

        Product savedProduct = productRepository.save(product);

        Assertions.assertSame(savedProduct.getName(), product.getName());
    }
}

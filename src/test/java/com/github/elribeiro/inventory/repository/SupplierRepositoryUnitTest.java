package com.github.elribeiro.inventory.repository;

import com.github.elribeiro.inventory.model.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("unit")
@Tag("unitTest")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SupplierRepositoryUnitTest {

    @Autowired
    SupplierRepository supplierRepository;

    @Test
    public void shouldCreateAndPersistASupplier (){
        Supplier supplier = Supplier.builder()
                .name("Revenda Samsung")
                .cnpj("12345678000122")
                .ie("123456789123")
                .build();

        Supplier savedSupplier = supplierRepository.save(supplier);

        Assertions.assertSame(savedSupplier.getName(), supplier.getName());
    }

}

package com.github.elribeiro.inventory.service;


import com.github.elribeiro.inventory.dto.SupplierDtoInput;
import com.github.elribeiro.inventory.dto.SupplierDtoOutput;
import com.github.elribeiro.inventory.exception.TechnicalException;
import com.github.elribeiro.inventory.model.Supplier;
import com.github.elribeiro.inventory.repository.SupplierRepository;
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

@ActiveProfiles("unit")
@Tag("unitTest")
@ExtendWith(MockitoExtension.class)
public class SuppliersServiceUnitTest {

    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    SuppliersService suppliersService;

    @BeforeEach
    public void setup(){
        Supplier supplier = Supplier.builder()
                .id(1)
                .cnpj("12345678000122")
                .ie("123456789123")
                .name("Samsung Supplier")
                .build();

        Mockito.when(supplierRepository.save(Mockito.any(Supplier.class))).thenReturn(supplier);
    }

    @Test
    public void shouldCreateAndReturnASupplier() throws TechnicalException {
        SupplierDtoInput input = SupplierDtoInput.builder()
                .name("Samsung Supplier")
                .cnpj("12345678000122")
                .ie("123456789012")
                .build();

        SupplierDtoOutput output = suppliersService.saveSupplier(input);

        Assertions.assertSame(input.getName(), output.getName());
        Assertions.assertSame(output.getId(), 1);
    }
}

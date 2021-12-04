package com.github.elribeiro.inventory.service;

import com.github.elribeiro.inventory.dto.ProductDtoInput;
import com.github.elribeiro.inventory.dto.ProductDtoOutput;
import com.github.elribeiro.inventory.exception.TechnicalException;
import com.github.elribeiro.inventory.model.Brand;
import com.github.elribeiro.inventory.model.Product;
import com.github.elribeiro.inventory.model.Supplier;
import com.github.elribeiro.inventory.repository.BrandRepository;
import com.github.elribeiro.inventory.repository.ProductRepository;
import com.github.elribeiro.inventory.repository.SupplierRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.times;

@ActiveProfiles("unit")
@Tag("unitTest")
@ExtendWith(MockitoExtension.class)
public class ProductsServiceUnitTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    BrandRepository brandRepository;

    @Mock
    SupplierRepository supplierRepository;

    @InjectMocks
    ProductsService productsService;

    @Test
    public void shouldSaveAndReturnProduct() throws TechnicalException {
        Brand brand = Brand.builder()
                .id(1)
                .name("Samsung")
                .build();

        Supplier supplier = Supplier.builder()
                .id(1)
                .name("Samsung Supplier")
                .cnpj("12345678000122")
                .ie("123456789")
                .build();

        Product product = Product.builder()
                .id(1)
                .name("Samsung N8")
                .photoUrl("https://local/photo1")
                .brand(brand)
                .supplier(supplier)
                .unitPrice(BigDecimal.valueOf(9L,22))
                .build();

        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        Mockito.when(supplierRepository.findById(Mockito.eq(1))).thenReturn(Optional.of(supplier));
        Mockito.when(brandRepository.findById(Mockito.eq(1))).thenReturn(Optional.of(brand));

        ProductDtoInput input = ProductDtoInput.builder()
                .name("Samsung N8")
                .brandId(1)
                .supplierId(1)
                .photoUrl("https://local/photo1")
                .unitPrice(BigDecimal.valueOf(9L,22))
                .build();

        ProductDtoOutput output = productsService.saveProduct(input);

        Assertions.assertSame(input.getName(), output.getName());
        Assertions.assertTrue(output.getId() == 1);

        Mockito.verify(brandRepository, times(1)).findById(Mockito.anyInt());
        Mockito.verify(productRepository, times(1)).save(Mockito.any(Product.class));
        Mockito.verify(supplierRepository, times(1)).findById(Mockito.anyInt());

    }

    @Test
    public void shouldNotSaveAndReturnProductWithNonExistingBrandId() throws TechnicalException {
        ProductDtoInput input = ProductDtoInput.builder()
                .name("Samsung N8")
                .brandId(2)
                .supplierId(1)
                .photoUrl("https://local/photo1")
                .unitPrice(BigDecimal.valueOf(9L,22))
                .build();

        Assertions.assertThrows(NoSuchElementException.class, () -> productsService.saveProduct(input));

        Mockito.verify(brandRepository, times(1)).findById(Mockito.eq(2));
    }

    @Test
    public void shouldNotSaveAndReturnProductWithNonExistingSupplierId() throws TechnicalException {
        ProductDtoInput input = ProductDtoInput.builder()
                .name("Samsung N8")
                .brandId(1)
                .supplierId(2)
                .photoUrl("https://local/photo1")
                .unitPrice(BigDecimal.valueOf(9L,22))
                .build();

        Assertions.assertThrows(NoSuchElementException.class, () -> productsService.saveProduct(input));

    }
}

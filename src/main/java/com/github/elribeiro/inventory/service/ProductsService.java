package com.github.elribeiro.inventory.service;


import com.github.elribeiro.inventory.dto.ProductDtoInput;
import com.github.elribeiro.inventory.dto.ProductDtoOutput;
import com.github.elribeiro.inventory.message.Messages;
import com.github.elribeiro.inventory.exception.TechnicalException;
import com.github.elribeiro.inventory.model.Product;
import com.github.elribeiro.inventory.repository.BrandRepository;
import com.github.elribeiro.inventory.repository.ProductRepository;
import com.github.elribeiro.inventory.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProductsService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    SupplierRepository supplierRepositoy;

    public ProductDtoOutput saveProduct (ProductDtoInput productDto) throws TechnicalException {

        Product product = Product.builder()
                .name(productDto.getName())
                .unitPrice(productDto.getUnitPrice())
                .brand(brandRepository.findById(productDto.getBrandId()).orElseThrow())
                .supplier(supplierRepositoy.findById(productDto.getSupplierId()).orElseThrow())
                .photoUrl(productDto.getPhotoUrl())
                .build();

        try {
            Product savedProduct = productRepository.save(product);
            return ProductDtoOutput.builder()
                    .name(savedProduct.getName())
                    .id(savedProduct.getId())
                    .build();
        }catch (DataAccessException e) {
            throw new TechnicalException(Messages.ERROR_SAVE_DATABASE.getMessage(), e);
        }
    }
}

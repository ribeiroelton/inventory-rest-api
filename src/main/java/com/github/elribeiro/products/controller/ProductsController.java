package com.github.elribeiro.products.controller;

import com.github.elribeiro.products.dto.ProductDtoInput;
import com.github.elribeiro.products.dto.ProductDtoOutput;
import com.github.elribeiro.products.exception.TechnicalException;
import com.github.elribeiro.products.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @PostMapping
    ResponseEntity<ProductDtoOutput> saveProduct(ProductDtoInput input) throws TechnicalException {
        ProductDtoOutput output = productsService.saveProduct(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }
}

package com.github.elribeiro.inventory.controller.v1;

import com.github.elribeiro.inventory.dto.ProductDtoInput;
import com.github.elribeiro.inventory.dto.ProductDtoOutput;
import com.github.elribeiro.inventory.exception.TechnicalException;
import com.github.elribeiro.inventory.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/products")
public class ProductsController {

    @Autowired
    ProductsService productsService;

    @PostMapping
    ResponseEntity<ProductDtoOutput> saveProduct(@Valid @RequestBody ProductDtoInput input) throws TechnicalException {
        ProductDtoOutput output = productsService.saveProduct(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }
}

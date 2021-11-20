package com.github.elribeiro.products.controller;

import com.github.elribeiro.products.dto.BrandDtoInput;
import com.github.elribeiro.products.dto.BrandDtoOutput;
import com.github.elribeiro.products.exception.TechnicalException;
import com.github.elribeiro.products.service.BrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v1/brands")
public class BrandsController {

    @Autowired
    private BrandsService brandsService;

    @PostMapping()
    ResponseEntity<BrandDtoOutput> saveBrand(@Valid @RequestBody BrandDtoInput input) throws TechnicalException {
        BrandDtoOutput output = brandsService.saveBrand(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }
}

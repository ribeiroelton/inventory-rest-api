package com.github.elribeiro.inventory.controller.v1;

import com.github.elribeiro.inventory.dto.BrandDtoInput;
import com.github.elribeiro.inventory.dto.BrandDtoOutput;
import com.github.elribeiro.inventory.exception.TechnicalException;
import com.github.elribeiro.inventory.service.BrandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/brands")
public class BrandsController {

    @Autowired
    private BrandsService brandsService;

    @PostMapping()
    public ResponseEntity<BrandDtoOutput> saveBrand(@Valid @RequestBody BrandDtoInput input) throws TechnicalException {
        BrandDtoOutput output = brandsService.saveBrand(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }
}

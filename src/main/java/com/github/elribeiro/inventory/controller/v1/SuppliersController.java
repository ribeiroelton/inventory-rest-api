package com.github.elribeiro.inventory.controller.v1;

import com.github.elribeiro.inventory.dto.SupplierDtoInput;
import com.github.elribeiro.inventory.dto.SupplierDtoOutput;
import com.github.elribeiro.inventory.exception.TechnicalException;
import com.github.elribeiro.inventory.service.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/suppliers")
public class SuppliersController {

    @Autowired
    SuppliersService suppliersService;

    @PostMapping
    ResponseEntity<SupplierDtoOutput> saveSupplier(@Valid @RequestBody SupplierDtoInput input) throws TechnicalException {
        SupplierDtoOutput output = suppliersService.saveSupplier(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(output);
    }
}

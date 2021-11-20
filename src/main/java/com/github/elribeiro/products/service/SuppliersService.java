package com.github.elribeiro.products.service;

import com.github.elribeiro.products.dto.SupplierDtoInput;
import com.github.elribeiro.products.dto.SupplierDtoOutput;
import com.github.elribeiro.products.message.Messages;
import com.github.elribeiro.products.exception.TechnicalException;
import com.github.elribeiro.products.model.Supplier;
import com.github.elribeiro.products.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SuppliersService {

    @Autowired
    private SupplierRepository supplierRepository;

    public SupplierDtoOutput saveSupplier(SupplierDtoInput supplierDtoInput) throws TechnicalException {
        Supplier supplier = Supplier.builder()
                .name(supplierDtoInput.getName())
                .cnpj(supplierDtoInput.getCnpj())
                .ie(supplierDtoInput.getIe())
                .build();

        try {
            Supplier savedSupplier = supplierRepository.save(supplier);

            return SupplierDtoOutput.builder()
                    .id(savedSupplier.getId())
                    .name(savedSupplier.getName())
                    .build();
        } catch (DataAccessException e) {
            throw new TechnicalException(Messages.ERROR_SAVE_DATABASE.getMessage(), e);
        }
    }

}

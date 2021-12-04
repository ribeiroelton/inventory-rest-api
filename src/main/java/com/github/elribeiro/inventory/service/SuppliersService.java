package com.github.elribeiro.inventory.service;

import com.github.elribeiro.inventory.dto.SupplierDtoInput;
import com.github.elribeiro.inventory.dto.SupplierDtoOutput;
import com.github.elribeiro.inventory.message.Messages;
import com.github.elribeiro.inventory.exception.TechnicalException;
import com.github.elribeiro.inventory.model.Supplier;
import com.github.elribeiro.inventory.repository.SupplierRepository;
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

package com.github.elribeiro.inventory.service;

import com.github.elribeiro.inventory.dto.BrandDtoInput;
import com.github.elribeiro.inventory.dto.BrandDtoOutput;
import com.github.elribeiro.inventory.message.Messages;
import com.github.elribeiro.inventory.exception.TechnicalException;
import com.github.elribeiro.inventory.model.Brand;
import com.github.elribeiro.inventory.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BrandsService {

    @Autowired
    private BrandRepository brandRepository;

    public BrandDtoOutput saveBrand(BrandDtoInput brandDtoInput) throws TechnicalException {
        Brand brand = Brand.builder()
                .name(brandDtoInput.getName())
                .build();

        try {
            Brand savedBrand = brandRepository.save(brand);

            return BrandDtoOutput.builder()
                    .id(savedBrand.getId())
                    .name(savedBrand.getName())
                    .build();
        }catch (DataAccessException e){
            throw new TechnicalException(Messages.ERROR_SAVE_DATABASE.getMessage(), e);
        }
    }
}

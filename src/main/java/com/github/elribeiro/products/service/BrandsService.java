package com.github.elribeiro.products.service;

import com.github.elribeiro.products.dto.BrandDtoInput;
import com.github.elribeiro.products.dto.BrandDtoOutput;
import com.github.elribeiro.products.message.Messages;
import com.github.elribeiro.products.exception.TechnicalException;
import com.github.elribeiro.products.model.Brand;
import com.github.elribeiro.products.repository.BrandRepository;
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

package com.github.elribeiro.products.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@ToString
@Getter
@AllArgsConstructor
public class ProductDtoInput {

    @NotNull
    @JsonAlias("supplier_id")
    private Integer supplierId;

    @NotNull
    @JsonAlias("brand_id")
    private Integer brandId;

    @NotNull
    private String name;

    @Length(max = 200)
    private String description;

    @NotNull
    @JsonAlias("unit_price")
    private BigDecimal unitPrice;

    @Length(max = 300)
    @JsonAlias("photo_url")
    private String photoUrl;
}

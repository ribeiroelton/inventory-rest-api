package com.github.elribeiro.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDtoInput {
    @Length(max = 50)
    @NotNull
    private String name;

    @Length(max = 14, min = 14)
    @NotNull
    private String cnpj;

    @Length(max = 14)
    @NotNull
    private String ie;

}

package com.github.elribeiro.products.dto;

import lombok.*;

@Builder
@ToString
@Getter
@AllArgsConstructor
public class ProductDtoOutput {
    private Integer id;
    private String name;
}

package com.github.elribeiro.inventory.dto;

import lombok.*;

@Builder
@ToString
@Getter
@AllArgsConstructor
public class ProductDtoOutput {
    private Integer id;
    private String name;
}

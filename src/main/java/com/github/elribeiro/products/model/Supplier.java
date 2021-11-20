package com.github.elribeiro.products.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Length(max = 50)
    @NotNull
    private String name;

    @Length(max = 14, min = 14)
    @NotNull
    private String cnpj;

    @Length(max = 14)
    @NotNull
    private String ie;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supplier")
    private List<Product> products;

}

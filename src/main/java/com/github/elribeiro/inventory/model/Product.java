package com.github.elribeiro.inventory.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @NotNull
    private Supplier supplier;

    @ManyToOne
    @NotNull
    private Brand brand;

    @Length(max = 50)
    @NotNull
    private String name;

    @Length(max = 200)
    private String description;

    @NotNull
    private BigDecimal unitPrice;

    @Length(max = 300)
    private String photoUrl;

}

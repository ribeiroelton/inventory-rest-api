package com.github.elribeiro.products.repository;

import com.github.elribeiro.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}

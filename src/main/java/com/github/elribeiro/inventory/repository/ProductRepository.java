package com.github.elribeiro.inventory.repository;

import com.github.elribeiro.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}

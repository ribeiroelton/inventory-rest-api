package com.github.elribeiro.products.repository;

import com.github.elribeiro.products.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}

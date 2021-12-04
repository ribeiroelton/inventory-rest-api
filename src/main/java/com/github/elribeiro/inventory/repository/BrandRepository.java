package com.github.elribeiro.inventory.repository;

import com.github.elribeiro.inventory.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}

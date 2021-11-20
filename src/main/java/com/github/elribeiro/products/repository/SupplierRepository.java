package com.github.elribeiro.products.repository;

import com.github.elribeiro.products.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}

package com.github.elribeiro.inventory.repository;

import com.github.elribeiro.inventory.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}

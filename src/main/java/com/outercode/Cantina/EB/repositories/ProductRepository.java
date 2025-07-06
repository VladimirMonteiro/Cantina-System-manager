package com.outercode.Cantina.EB.repositories;

import com.outercode.Cantina.EB.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

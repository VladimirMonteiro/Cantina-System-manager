package com.outercode.Cantina.EB.repositories;

import com.outercode.Cantina.EB.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAll(Pageable pageable);
}

package com.outercode.Cantina.EB.repositories;

import com.outercode.Cantina.EB.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}

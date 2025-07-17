package com.outercode.Cantina.EB.dto.order;

import com.outercode.Cantina.EB.entities.Client;
import com.outercode.Cantina.EB.entities.OrderItem;
import com.outercode.Cantina.EB.entities.enums.OrderStatus;

import java.util.Date;
import java.util.Set;

public record ResponseOrderDTO(Long id, Date date, Double totalPrice, OrderStatus orderStatus, Client client, Set<OrderItem> items ) {
}

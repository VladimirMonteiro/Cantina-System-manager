package com.outercode.Cantina.EB.dto.order;

import com.outercode.Cantina.EB.entities.Client;

import com.outercode.Cantina.EB.entities.enums.OrderStatus;

import java.util.Date;


public record ResponseOrderDTO(Long id, Date date, Double totalPrice, OrderStatus orderStatus, Client client) {
}

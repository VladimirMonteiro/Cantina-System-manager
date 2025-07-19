package com.outercode.Cantina.EB.utils;

import com.outercode.Cantina.EB.dto.order.CreateOrderDTO;
import com.outercode.Cantina.EB.dto.order.CreateOrderItemDTO;
import com.outercode.Cantina.EB.entities.Order;
import com.outercode.Cantina.EB.entities.enums.OrderStatus;

import java.util.Date;
import java.util.Set;

import static com.outercode.Cantina.EB.utils.InitClientConstants.CLIENT;


public class InitOrderConstants {

    public static final Order ORDER = new Order(1L, new Date(), 4.5,OrderStatus.PAID, CLIENT);
    public static final CreateOrderItemDTO CREATE_ORDER_ITEM_DTO = new CreateOrderItemDTO(1L, 1, 4.5);
    public static final CreateOrderDTO CREATE_ORDER_DTO = new CreateOrderDTO(1L, new Date(), 4.5, OrderStatus.UNPAID, Set.of(CREATE_ORDER_ITEM_DTO));
}

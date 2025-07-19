package com.outercode.Cantina.EB.dto.order;



import com.outercode.Cantina.EB.entities.enums.OrderStatus;
import jakarta.validation.Valid;

import java.util.Date;
import java.util.Set;

public record CreateOrderDTO(Long clientId,
                             Date date,
                             Double totalPrice,
                             OrderStatus orderStatus,
                             @Valid Set<CreateOrderItemDTO> items) {
}

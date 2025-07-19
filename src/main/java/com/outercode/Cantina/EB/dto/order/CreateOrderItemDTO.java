package com.outercode.Cantina.EB.dto.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateOrderItemDTO(
        Long productId,
        @NotNull(message = "A quantidade é obrigatória.")
        @Min(value = 1, message = "Quantidade miníma deve ser 1.")
        Integer quantity,
        @NotNull(message = "O preço é obrigatório.")
        Double price
) {}
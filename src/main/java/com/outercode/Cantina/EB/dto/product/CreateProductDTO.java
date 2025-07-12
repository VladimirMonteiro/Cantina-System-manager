package com.outercode.Cantina.EB.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateProductDTO(
        @NotBlank(message = "O nome é obrigatório.")
        String name,

        @NotNull(message = "O valor é obrigatório")
        Double price
) {
}

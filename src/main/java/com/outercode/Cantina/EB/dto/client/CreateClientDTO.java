package com.outercode.Cantina.EB.dto.client;

import com.outercode.Cantina.EB.entities.enums.Company;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateClientDTO(
        @NotBlank(message = "O nome de guerra é obrigatório!") String warName,
        Integer soldierNumber,
        @NotBlank(message = "A graduação é obrigatória!")
        String grad,
        @NotBlank(message = "O telefone é obrigatório!") String phone,
        @NotNull(message = "A companhia é obrigatória!") Company company) {
}

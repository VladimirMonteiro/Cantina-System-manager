package com.outercode.Cantina.EB.dto.client;

import com.outercode.Cantina.EB.entities.enums.Company;

public record ResponseClientDTO(Long id, String warName, Integer soldierNumber, String phone, Company company) {
}

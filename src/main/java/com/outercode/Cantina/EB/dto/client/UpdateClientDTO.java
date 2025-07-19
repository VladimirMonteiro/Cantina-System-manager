package com.outercode.Cantina.EB.dto.client;

import com.outercode.Cantina.EB.entities.enums.Company;

public record UpdateClientDTO(String warName, Integer soldierNumber, String grad ,String phone, Company company) {
}

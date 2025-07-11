package com.outercode.Cantina.EB.utils;

import com.outercode.Cantina.EB.dto.client.CreateClientDTO;
import com.outercode.Cantina.EB.dto.client.ResponseClientDTO;
import com.outercode.Cantina.EB.entities.Client;
import com.outercode.Cantina.EB.entities.enums.Company;

public class InitClientConstants {

    public static final ResponseClientDTO CLIENT_DTO = new ResponseClientDTO(1L, "vladimir", null, "51995005000", Company.TWOCIA);
    public static final Client CLIENT = new Client(1L, "vladimir", null, "51995005000", Company.TWOCIA);
    public static final CreateClientDTO CREATE_CLIENT_DTO = new CreateClientDTO("vladimir", null, "51995005000", Company.TWOCIA);
    public static final CreateClientDTO INVALID_CLIENT_DTO = new CreateClientDTO("", null, "", Company.TWOCIA);
}

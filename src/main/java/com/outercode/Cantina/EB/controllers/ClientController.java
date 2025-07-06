package com.outercode.Cantina.EB.controllers;

import com.outercode.Cantina.EB.dto.client.ResponseClientDTO;
import com.outercode.Cantina.EB.services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/clients", produces = "application/json")
@Tag(name = "Client", description = "Controller from manage of client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseClientDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(page, size));
    }
}

package com.outercode.Cantina.EB.controllers;

import com.outercode.Cantina.EB.controllers.exceptions.ResponseDTO;
import com.outercode.Cantina.EB.dto.client.CreateClientDTO;
import com.outercode.Cantina.EB.dto.client.ResponseClientDTO;
import com.outercode.Cantina.EB.services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid CreateClientDTO obj) {
        clientService.create(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(HttpStatus.CREATED.value(), "Cliente cadastrado com sucesso!"));
    }
}

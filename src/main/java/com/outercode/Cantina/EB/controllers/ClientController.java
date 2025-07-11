package com.outercode.Cantina.EB.controllers;

import com.outercode.Cantina.EB.config.ModelMapperConfig;
import com.outercode.Cantina.EB.controllers.exceptions.ResponseDTO;
import com.outercode.Cantina.EB.dto.client.CreateClientDTO;
import com.outercode.Cantina.EB.dto.client.ResponseClientDTO;
import com.outercode.Cantina.EB.entities.Client;
import com.outercode.Cantina.EB.services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clients", produces = "application/json")
@Tag(name = "Client", description = "Controller from manage of client")
public class ClientController {

    private final ClientService clientService;
    private final ModelMapper mapper;

    public ClientController(ClientService clientService, ModelMapper mapper) {
        this.clientService = clientService;
        this.mapper = mapper;
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

    @GetMapping("/{id}")
    public ResponseEntity<ResponseClientDTO> findById(@PathVariable("id") Long id) {
        Client client = clientService.findById(id);
        ResponseClientDTO dto = new ResponseClientDTO(client.getId(),
                client.getWarName(), client.getSoldierNumber(), client.getPhone(), client.getCompany());
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK.value(), "Cliente removido com sucesso."));
    }
}

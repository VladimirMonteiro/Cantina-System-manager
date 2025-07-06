package com.outercode.Cantina.EB.services;

import com.outercode.Cantina.EB.dto.client.ResponseClientDTO;
import com.outercode.Cantina.EB.repositories.ClientRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ResponseClientDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("warName")));

        return clientRepository.findAll(pageable).getContent().stream()
                .map(client -> new ResponseClientDTO(
                        client.getId(),
                        client.getWarName(),
                        client.getSoldierNumber(),
                        client.getPhone(),
                        client.getCompany()
                ))
                .toList();
    }
}

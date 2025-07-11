package com.outercode.Cantina.EB.services;

import com.outercode.Cantina.EB.dto.client.CreateClientDTO;
import com.outercode.Cantina.EB.dto.client.ResponseClientDTO;
import com.outercode.Cantina.EB.entities.Client;
import com.outercode.Cantina.EB.repositories.ClientRepository;
import com.outercode.Cantina.EB.services.exceptions.ObjectNotFoundException;
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

    public Client create(CreateClientDTO obj) {
        Client newClient = new Client();

        newClient.setWarName(obj.warName());
        newClient.setSoldierNumber(obj.soldierNumber());
        newClient.setPhone(obj.phone());
        newClient.setCompany(obj.company());

        return clientRepository.save(newClient);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado."));
    }
}

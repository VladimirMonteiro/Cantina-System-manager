package com.outercode.Cantina.EB.services;

import com.outercode.Cantina.EB.dto.client.ResponseClientDTO;
import com.outercode.Cantina.EB.entities.Client;
import com.outercode.Cantina.EB.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.outercode.Cantina.EB.utils.InitClientConstants.CLIENT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    void FindAllClients_WhenValidParameter_ReturnListOfClients() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Client> mockPage = new PageImpl<>(List.of(CLIENT)); //

        when(clientRepository.findAll(any(Pageable.class))).thenReturn(mockPage);

        List<ResponseClientDTO> result = clientService.findAll(pageable.getPageNumber(), pageable.getPageSize());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("vladimir", result.getFirst().warName());
    }
}
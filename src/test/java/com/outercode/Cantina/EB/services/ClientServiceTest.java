package com.outercode.Cantina.EB.services;

import com.outercode.Cantina.EB.dto.client.ResponseClientDTO;
import com.outercode.Cantina.EB.dto.client.UpdateClientDTO;
import com.outercode.Cantina.EB.entities.Client;
import com.outercode.Cantina.EB.entities.enums.Company;
import com.outercode.Cantina.EB.repositories.ClientRepository;
import com.outercode.Cantina.EB.services.exceptions.ObjectNotFoundException;
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
import java.util.Optional;

import static com.outercode.Cantina.EB.utils.InitClientConstants.*;
import static org.assertj.core.api.Assertions.*;
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

    @Test
    void CreateClient_WithValidData_ReturnSuccessMessage() {
        when(clientRepository.save(any())).thenReturn(CLIENT);

        Client result = clientService.create(CREATE_CLIENT_DTO);

        assertNotNull(result);
        assertEquals(result.getClass(), CLIENT.getClass());
        assertEquals(result.getWarName(), CLIENT.getWarName());
        assertEquals(result.getPhone(), CLIENT.getPhone());
        assertEquals(result.getCompany(), CLIENT.getCompany());
        assertEquals(result.getSoldierNumber(), CLIENT.getSoldierNumber());
        assertEquals(result.getGrad(), CLIENT.getGrad());
    }

    @Test
    void CreateClient_WithInvalidData_ThrowsException() {
        when(clientRepository.save(INVALID_CLIENT)).thenThrow(new IllegalArgumentException());

        assertThatThrownBy(() -> clientService.create(INVALID_CREATE_CLIENT))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void findClientById_WithValidId_ThenReturnClient() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(CLIENT));

        Client response = clientService.findById(CLIENT.getId());

        assertNotNull(response);
        assertEquals(response.getId(), CLIENT.getId());
        assertEquals(response.getWarName(), CLIENT.getWarName());
        assertEquals(response.getSoldierNumber(), CLIENT.getSoldierNumber());
        assertEquals(response.getGrad(), CLIENT.getGrad());
        assertEquals(response.getCompany(), CLIENT.getCompany());
        verify(clientRepository, times(1)).findById(CLIENT.getId());
    }

    @Test
    void findClientById_WithInvalidId_ThrowsObjectNotFound() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Client> sut =  clientRepository.findById(1L);
        assertThat(sut).isEmpty();
        verify(clientRepository).findById(1L);
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void deleteClient_WithValidId_ThenReturnSuccessMessage() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(CLIENT));
        assertThatCode(()-> clientService.delete(1L)).doesNotThrowAnyException();
    }

    @Test
    void deleteClient_WithInvalidId_ThrowsObjectNotFoundException() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(CLIENT));
        doThrow(new ObjectNotFoundException("Cliente não encontrado.")).when(clientRepository).deleteById(99L);
        assertThatThrownBy(() -> clientService.delete(99L)).isInstanceOf(ObjectNotFoundException.class);
    }

    @Test
    void updateClient_ThenReturnSuccessMessage() {
        when(clientRepository.findById(anyLong())).thenReturn(Optional.of(CLIENT));
        when(clientRepository.save(CLIENT)).thenReturn(CLIENT);

        UpdateClientDTO updatedClient = new UpdateClientDTO(
                "updated war name",
                100,
                "SD",
                "0000-0000",
                Company.TWOCIA
        );

        Client response = clientService.update(anyLong(), updatedClient);

        assertThat(response).isNotNull();
        assertThat(response.getWarName()).isEqualTo(updatedClient.warName());
        assertThat(response.getSoldierNumber()).isEqualTo(updatedClient.soldierNumber());
        assertThat(response.getGrad()).isEqualTo(updatedClient.grad());
        assertThat(response.getPhone()).isEqualTo(updatedClient.phone());
        assertThat(response.getCompany()).isEqualTo(updatedClient.company());
    }
}
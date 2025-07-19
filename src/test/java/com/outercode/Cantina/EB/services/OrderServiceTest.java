package com.outercode.Cantina.EB.services;

import com.outercode.Cantina.EB.dto.order.ResponseOrderDTO;
import com.outercode.Cantina.EB.entities.Order;
import com.outercode.Cantina.EB.entities.OrderItem;
import com.outercode.Cantina.EB.repositories.OrderItemRepository;
import com.outercode.Cantina.EB.repositories.OrderRepository;
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

import static com.outercode.Cantina.EB.utils.InitClientConstants.CLIENT;
import static com.outercode.Cantina.EB.utils.InitOrderConstants.CREATE_ORDER_DTO;
import static com.outercode.Cantina.EB.utils.InitOrderConstants.ORDER;
import static com.outercode.Cantina.EB.utils.InitProductConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {


    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ClientService clientService;

    @Mock
    private ProductService productService;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Test
    void findAll_WithAllParameter_ReturnsAllOrders() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Order> mockPage = new PageImpl<>(List.of(ORDER)); //

        when(orderRepository.findAll(any(Pageable.class))).thenReturn(mockPage);

        List<ResponseOrderDTO> result = orderService.findAll(pageable.getPageNumber(), pageable.getPageSize());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(result.getFirst().orderStatus(),ORDER.getOrderStatus());
        assertEquals(result.getFirst().totalPrice(), ORDER.getTotalPrice());
        assertEquals(result.getFirst().id(), ORDER.getId());
        assertEquals(result.getFirst().client(), ORDER.getClient());
    }

    @Test
    void findOderById_WithValidId_ReturnOrder() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(ORDER));

        Order result = orderService.findById(1L);

        assertNotNull(result);

        assertEquals(result.getOrderStatus(),ORDER.getOrderStatus());
        assertEquals(result.getTotalPrice(), ORDER.getTotalPrice());
        assertEquals(result.getItems(), ORDER.getItems());
        assertEquals(result.getId(), ORDER.getId());
        assertEquals(result.getClient(), ORDER.getClient());
    }

    @Test
    void findOrderById_WithInvalidId_ThenReturnObjectNotFoundException() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Order> sut =  orderRepository.findById(1L);
        assertThat(sut).isEmpty();
        verify(orderRepository).findById(1L);
        verify(orderRepository, times(1)).findById(1L);
    }

    @Test
    void createOrder_WithValidData_ReturnSuccessMessage() {
       when(clientService.findById(anyLong())).thenReturn(CLIENT);
       when(orderRepository.save(any())).thenReturn(ORDER);
       when(productService.findById(anyLong())).thenReturn(PRODUCT);
       when(orderItemRepository.save(any())).thenReturn(new OrderItem(ORDER, PRODUCT, 5.0, 1 ));


       Order result = orderService.create(CREATE_ORDER_DTO);

       assertNotNull(result);
       assertThat(result.getClient()).isEqualTo(CLIENT);
       assertThat(result.getOrderStatus()).isEqualTo(ORDER.getOrderStatus());
       assertThat(result.getItems().size()).isEqualTo(CREATE_ORDER_DTO.items().size());
       assertThat(result.getDate()).isEqualTo(ORDER.getDate());
       assertThat(result.getTotalPrice()).isEqualTo(ORDER.getTotalPrice());
       verify(clientService, times(1)).findById(anyLong());
       verify(productService, times(1)).findById(anyLong());
       verify(orderItemRepository, times(1)).save(any());
       verify(orderRepository, times(1)).save(ORDER);
    }

    @Test
    void deleteOrder_WithValidId_thenReturnSuccessMessage() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(ORDER));
        assertThatCode(()-> orderService.delete(1L)).doesNotThrowAnyException();
    }

    @Test
    void deleteOrder_WithInvalidId_thenReturnObjectNotFoundException() {
        when(orderRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> orderService.delete(99L))
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Pedido não encontrado");
    }
}
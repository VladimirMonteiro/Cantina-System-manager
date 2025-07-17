package com.outercode.Cantina.EB.services;

import com.outercode.Cantina.EB.dto.order.ResponseOrderDTO;
import com.outercode.Cantina.EB.entities.Order;
import com.outercode.Cantina.EB.entities.Product;
import com.outercode.Cantina.EB.repositories.OrderRepository;
import org.aspectj.weaver.ast.Or;
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

import static com.outercode.Cantina.EB.utils.InitOrderConstants.ORDER;
import static com.outercode.Cantina.EB.utils.InitProductConstants.INVALID_CREATE_PRODUCT_DTO;
import static com.outercode.Cantina.EB.utils.InitProductConstants.INVALID_PRODUCT;
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
        assertEquals(result.getFirst().items(), ORDER.getItems());
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
}
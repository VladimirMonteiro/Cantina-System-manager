package com.outercode.Cantina.EB.services;

import com.outercode.Cantina.EB.dto.order.ResponseOrderDTO;
import com.outercode.Cantina.EB.entities.Order;
import com.outercode.Cantina.EB.repositories.OrderRepository;
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

import static com.outercode.Cantina.EB.utils.InitOrderConstants.ORDER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
}
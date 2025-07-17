package com.outercode.Cantina.EB.services;

import com.outercode.Cantina.EB.dto.order.CreateOrderDTO;
import com.outercode.Cantina.EB.dto.order.ResponseOrderDTO;
import com.outercode.Cantina.EB.entities.Order;
import com.outercode.Cantina.EB.repositories.OrderRepository;
import com.outercode.Cantina.EB.services.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<ResponseOrderDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("date")));
        return orderRepository.findAll(pageable).getContent().stream()
                .map(order -> new ResponseOrderDTO(
                        order.getId(),
                        order.getDate(),
                        order.getTotalPrice(),
                        order.getOrderStatus(),
                        order.getClient(),
                        order.getItems()
                )).toList();
    }

    public Order findById(Long id) {
       return orderRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Pedido não encontrado"));
    }
}

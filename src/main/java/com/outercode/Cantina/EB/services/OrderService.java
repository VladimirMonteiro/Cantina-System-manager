package com.outercode.Cantina.EB.services;

import com.outercode.Cantina.EB.dto.order.CreateOrderDTO;
import com.outercode.Cantina.EB.dto.order.CreateOrderItemDTO;
import com.outercode.Cantina.EB.dto.order.ResponseOrderDTO;
import com.outercode.Cantina.EB.entities.Client;
import com.outercode.Cantina.EB.entities.Order;
import com.outercode.Cantina.EB.entities.OrderItem;
import com.outercode.Cantina.EB.entities.Product;
import com.outercode.Cantina.EB.repositories.OrderItemRepository;
import com.outercode.Cantina.EB.repositories.OrderRepository;
import com.outercode.Cantina.EB.services.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    private final ClientService clientService;

    private final ProductService productService;

    private final OrderItemRepository orderItemRepository;

    public OrderService(OrderRepository orderRepository, ClientService clientService, ProductService productService, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.productService = productService;
        this.orderItemRepository = orderItemRepository;
    }

    public List<ResponseOrderDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("date")));
        return orderRepository.findAll(pageable).getContent().stream()
                .map(order -> new ResponseOrderDTO(
                        order.getId(),
                        order.getDate(),
                        order.getTotalPrice(),
                        order.getOrderStatus(),
                        order.getClient()
                )).toList();
    }

    public Order findById(Long id) {
       return orderRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Pedido não encontrado"));
    }

    @Transactional
    public Order create(CreateOrderDTO createOrderDTO) {
        // Validar cliente
        Client client = clientService.findById(createOrderDTO.clientId());
        if (client == null) {
            throw new ObjectNotFoundException("Cliente não encontrado para o ID: " + createOrderDTO.clientId());
        }

        // Criar pedido
        Order order = new Order();
        order.setClient(client);
        order.setDate(createOrderDTO.date());
        order.setOrderStatus(createOrderDTO.orderStatus());
        order.setTotalPrice(0.0); // Inicializar com 0, será atualizado depois

        order = orderRepository.save(order); // Salvar para gerar ID

        // Criar e associar itens de pedido
        double totalOrderPrice = 0.0;
        for (CreateOrderItemDTO itemDTO : createOrderDTO.items()) {
            Product product = productService.findById(itemDTO.productId());

            // Criar item do pedido
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.quantity());
            orderItem.setPrice(itemDTO.price());

            // Adicionar item ao pedido
            order.getItems().add(orderItem);

            // Salvar item
            orderItemRepository.save(orderItem);

            // Calcular preço total
                totalOrderPrice += itemDTO.price() * itemDTO.quantity();
        }

        // Atualizar preço total e salvar pedido
        order.setTotalPrice(totalOrderPrice);
        return orderRepository.save(order);
    }
}

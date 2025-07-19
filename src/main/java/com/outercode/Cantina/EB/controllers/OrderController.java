package com.outercode.Cantina.EB.controllers;

import com.outercode.Cantina.EB.controllers.exceptions.ResponseDTO;
import com.outercode.Cantina.EB.dto.order.CreateOrderDTO;
import com.outercode.Cantina.EB.dto.order.ResponseOrderDTO;
import com.outercode.Cantina.EB.entities.Order;
import com.outercode.Cantina.EB.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders", produces = "application/json")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseOrderDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseOrderDTO> findById(@PathVariable("id") Long id) {
        Order order = orderService.findById(id);
        ResponseOrderDTO orderDTO = new ResponseOrderDTO
                (order.getId(),
                 order.getDate(),
                 order.getTotalPrice(),
                 order.getOrderStatus(),
                 order.getClient());
        return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid CreateOrderDTO obj) {
        orderService.create(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(HttpStatus.CREATED.value(), "Pedido cadastrado com sucesso."));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> delete(@PathVariable("id") Long id) {
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK.value(), "Pedido excluido com sucesso."));
    }
}

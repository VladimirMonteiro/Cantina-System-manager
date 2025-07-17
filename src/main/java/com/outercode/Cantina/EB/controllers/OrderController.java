package com.outercode.Cantina.EB.controllers;

import com.outercode.Cantina.EB.dto.order.ResponseOrderDTO;
import com.outercode.Cantina.EB.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}

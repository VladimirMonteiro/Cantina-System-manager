package com.outercode.Cantina.EB.controllers;

import com.outercode.Cantina.EB.dto.product.ResponseProductDTO;
import com.outercode.Cantina.EB.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = "application/json")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseProductDTO>> findAll(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(page, size));
    }
}

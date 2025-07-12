package com.outercode.Cantina.EB.controllers;

import com.outercode.Cantina.EB.controllers.exceptions.ResponseDTO;
import com.outercode.Cantina.EB.dto.product.CreateProductDTO;
import com.outercode.Cantina.EB.dto.product.ResponseProductDTO;
import com.outercode.Cantina.EB.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@RequestBody @Valid CreateProductDTO obj) {
        productService.create(obj);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(HttpStatus.CREATED.value(),
                "Produto cadastrado com sucesso."));
    }
}

package com.outercode.Cantina.EB.services;


import com.outercode.Cantina.EB.dto.product.ResponseProductDTO;
import com.outercode.Cantina.EB.entities.Product;
import com.outercode.Cantina.EB.repositories.ProductRepository;
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

import static com.outercode.Cantina.EB.utils.InitProductConstants.PRODUCT;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void findAllProduct_WithValidParameter_ThenReturnListOfProducts() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> mockPage = new PageImpl<>(List.of(PRODUCT)); //

        when(productRepository.findAll(any(Pageable.class))).thenReturn(mockPage);

        List<ResponseProductDTO> result = productService.findAll(pageable.getPageNumber(), pageable.getPageSize());

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Pastel", result.getFirst().name());
        assertEquals(4.50,result.getFirst().price());
        assertEquals(1, result.getFirst().id());
    }
}
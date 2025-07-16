package com.outercode.Cantina.EB.services;


import com.outercode.Cantina.EB.dto.product.ResponseProductDTO;


import com.outercode.Cantina.EB.dto.product.UpdateProductDTO;
import com.outercode.Cantina.EB.entities.Product;
import com.outercode.Cantina.EB.repositories.ProductRepository;
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
import static com.outercode.Cantina.EB.utils.InitProductConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

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

    @Test
    void createProduct_WithValidData_ThenReturnSuccessMessage() {
        when(productRepository.save(any())).thenReturn(PRODUCT);

        Product result = productService.create(CREATE_PRODUCT_DTO);

        assertNotNull(result);
        assertThat(result.getName()).isEqualTo(PRODUCT.getName());
        assertThat(result.getPrice()).isEqualTo(PRODUCT.getPrice());
        assertThat(result).isInstanceOf(Product.class);
    }

    @Test
    void createProduct_WithInvalidData_ReturnsThrowsExeception() {
        when(productRepository.save(INVALID_PRODUCT)).thenThrow(new IllegalArgumentException());


        assertThatThrownBy(() -> productService.create(INVALID_CREATE_PRODUCT_DTO))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void  findProduct_WithValidId_ThenReturnProduct() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));

        Product result = productService.findById(PRODUCT.getId());

        assertNotNull(result);
        assertThat(result.getId()).isEqualTo(PRODUCT.getId());
        assertThat(result.getName()).isEqualTo(PRODUCT.getName());
        assertThat(result.getPrice()).isEqualTo(PRODUCT.getPrice());
        verify(productRepository, times(1)).findById(PRODUCT.getId());
        assertThat(result).isInstanceOf(Product.class);
    }

    @Test
    void findProduct_WithInvalidId_ThrowsObjectNotFoundException() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Product> sut =  productRepository.findById(1L);
        assertThat(sut).isEmpty();
        verify(productRepository).findById(1L);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void deleteProduct_WithValidId_ThenReturnSuccessMessage() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));
        assertThatCode(()-> productService.delete(1L)).doesNotThrowAnyException();
    }

    @Test
    void deleteProduct_WithInvalidId_ThrowsObjectNotFoundException() {
        when(productRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productService.delete(99L))
                .isInstanceOf(ObjectNotFoundException.class)
                .hasMessage("Produto não encontrado.");
    }

    @Test
    void updateProduct_withValidData_ThenReturnSuccessMessage() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(PRODUCT));

        UpdateProductDTO updateProductDTO = new UpdateProductDTO("NameUpdate", 5.00);
        Product updatedProduct = new Product();
        updatedProduct.setName(updatedProduct.getName());
        updatedProduct.setPrice(updatedProduct.getPrice());

        when(productRepository.save(any())).thenReturn(updatedProduct);

        Product result = productService.update(1L, updateProductDTO);

        assertNotNull(result);
        assertThat(result.getName()).isEqualTo(updatedProduct.getName());
        assertThat(result.getPrice()).isEqualTo(updatedProduct.getPrice());
        assertThat(result).isInstanceOf(Product.class);
    }
}
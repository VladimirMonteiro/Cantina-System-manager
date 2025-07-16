package com.outercode.Cantina.EB.services;

import com.outercode.Cantina.EB.dto.product.CreateProductDTO;
import com.outercode.Cantina.EB.dto.product.ResponseProductDTO;
import com.outercode.Cantina.EB.entities.Product;
import com.outercode.Cantina.EB.repositories.ProductRepository;
import com.outercode.Cantina.EB.services.exceptions.ObjectNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ResponseProductDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc("name")));

        return productRepository.findAll(pageable).getContent().stream().map(product -> new ResponseProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice()
        )).toList();
    }

    public Product create(CreateProductDTO obj) {
        Product newProduct = new Product();

        newProduct.setName(obj.name());
        newProduct.setPrice(obj.price());
        return productRepository.save(newProduct);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Produto não encontrado."));
    }

    public void delete(Long id) {
        Product product = this.findById(id);
        productRepository.deleteById(product.getId());
    }
}

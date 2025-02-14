package com.example.mhsolution.service.services;

import com.example.mhsolution.domain.data.tables.pojos.Products;
import com.example.mhsolution.domain.data.mappers.product.ProductMapper;
import com.example.mhsolution.domain.data.responses.product.ProductResponse;
import com.example.mhsolution.repository.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductResponse> getAllProducts() {
        List<Products> products = productRepository.findAll();

        return products.stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductById(Integer id) {
        Products product = productRepository.findById(id);

        if (product == null) {
            throw new RuntimeException("Product with ID " + id + " not found");
        }

        return productMapper.toResponse(product);
    }
}


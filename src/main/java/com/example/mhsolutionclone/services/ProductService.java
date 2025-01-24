package com.example.mhsolutionclone.services;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Products;
import com.example.mhsolutionclone.data.mapper.ProductMapper;
import com.example.mhsolutionclone.data.response.ProductResponse;
import com.example.mhsolutionclone.repositories.ProductRepository;
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
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductBySeoId(String seoId) {
        Products product = productRepository.findBySeoId(seoId);

        if (product == null) {
            throw new RuntimeException("Product with seoId " + seoId + " not found");
        }

        return productMapper.toProductResponse(product);
    }
}


package com.example.mhsolutionclone.services;

import com.example.mhsolution.mhsolutionclone.jooq.tables.pojos.Products;
import com.example.mhsolutionclone.data.mapper.ProductMapper;
import com.example.mhsolutionclone.data.response.ProductContentResponse;
import com.example.mhsolutionclone.data.response.ProductInfoResponse;
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
                .map(this::getProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductBySeoId(String seoId) {
        Products product = productRepository.findBySeoId(seoId);

        if (product == null) {
            throw new RuntimeException("Product with seoId " + seoId + " not found");
        }

        return getProductResponse(product);
    }

    private ProductResponse getProductResponse(Products product) {
        List<ProductContentResponse> contents = productRepository.findContentsByProductId(product.getId()).stream()
                .map(productMapper::toContentResponse)
                .collect(Collectors.toList());

        List<ProductInfoResponse> infos = productRepository.findInfosByProductId(product.getId()).stream()
                .map(productMapper::toInfoResponse)
                .collect(Collectors.toList());

        return productMapper.toProductResponse(product, contents, infos);
    }
}


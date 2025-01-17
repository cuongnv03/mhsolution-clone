package com.example.mhsolutionclone.controllers;

import com.example.mhsolutionclone.data.response.ProductResponse;
import com.example.mhsolutionclone.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{seoId}")
    public ResponseEntity<ProductResponse> getProductBySeoId(@PathVariable String seoId) {
        ProductResponse product = productService.getProductBySeoId(seoId);
        return ResponseEntity.ok(product);
    }
}


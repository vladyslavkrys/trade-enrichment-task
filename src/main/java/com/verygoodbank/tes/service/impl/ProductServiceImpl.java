package com.verygoodbank.tes.service.impl;

import com.verygoodbank.tes.model.Product;
import com.verygoodbank.tes.repository.ProductRepository;
import com.verygoodbank.tes.service.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductServiceImpl implements ProductService {
    final ProductRepository productRepository;

    @Value("${product.file-path}")
    String productDataFilePath;

    @Override
    public String getProductName(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.map(Product::getName).orElse("Missing Product Name");
    }

    @PostConstruct
    public void loadProductData() {
        try (Stream<String> stream = Files.lines(Paths.get(productDataFilePath))) {
            stream.skip(1).forEach(this::insertProduct);
            log.info("Product data was uploaded.");
        } catch (IOException e) {
            log.error("Failed to load product data: ", e);
            throw new RuntimeException("Failed to load product data: " + e.getMessage(), e);
        }
    }

    private void insertProduct(String line) {
        String[] parts = line.split(",");
        Long id = Long.parseLong(parts[0]);
        String productName = parts[1];
        productRepository.save(
                Product.builder()
                        .withId(id)
                        .withName(productName).build()
        );
    }
}
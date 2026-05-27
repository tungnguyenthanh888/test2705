package com.test.productservice.service;

import com.test.productservice.dto.request.ProductRequest;
import com.test.productservice.dto.response.ProductResponse;
import com.test.productservice.entity.Product;
import com.test.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repository;

    public Product createProduct(ProductRequest request)
    {
        Product newProduct = new Product();
        newProduct.setProductName(request.getName());
        newProduct.setQuantity(request.getQuantity());
        newProduct.setPrice(request.getPrice());
        return repository.save(newProduct);
    }

    public Optional<Product> findProductById(long id)
    {
        return repository.findById(id);
    }
}

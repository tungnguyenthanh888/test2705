package com.test.productservice.controller;

import com.test.productservice.dto.request.ProductRequest;
import com.test.productservice.dto.response.ApiResponse;
import com.test.productservice.entity.Product;
import com.test.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    @Autowired
    private ProductService service;

    @GetMapping
    public String test()
    {
        return "Hello from inventory service";
    }

    @PostMapping
    public String createProduct(@RequestBody ProductRequest request)
    {
        service.createProduct(request);
        return "Tao product thanh cong";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") long id) {
        return service.findProductById(id)
                .map(product -> ResponseEntity.ok(
                        ApiResponse.builder()
                                .message("Tìm thấy product có id: " + id)
                                .status(HttpStatus.OK)
                                .data(product)
                                .build()
                ))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(ApiResponse.builder()
                                .message("Không tìm thấy product với id: " + id)
                                .status(HttpStatus.NOT_FOUND)
                                .build()
                        ));
    }
}

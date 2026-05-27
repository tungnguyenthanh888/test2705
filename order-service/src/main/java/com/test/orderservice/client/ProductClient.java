package com.test.orderservice.client;

import com.test.orderservice.dto.response.ApiResponse;
import com.test.orderservice.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="inventory-service")
public interface ProductClient {
    @GetMapping("/api/inventory/{id}")
    ApiResponse<ProductResponse> getProductById(@PathVariable("id") Long id);
}

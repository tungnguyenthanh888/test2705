package com.test.orderservice.controller;

import com.test.orderservice.dto.request.OrderRequest;
import com.test.orderservice.dto.response.ApiResponse;
import com.test.orderservice.entity.Order;
import com.test.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request)
    {
        Order newOrder = orderService.createOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        ApiResponse.builder()
                                .message("Tao moi don hang thanh cong.")
                                .status(HttpStatus.CREATED)
                                .data(newOrder)
                                .build()
                );
    }

    @GetMapping
    public String test()
    {
        return "Hello from order service.";
    }
}

package com.test.orderservice.service;

import com.test.orderservice.client.ProductClient;
import com.test.orderservice.dto.request.OrderRequest;
import com.test.orderservice.dto.response.ApiResponse;
import com.test.orderservice.dto.response.ProductResponse;
import com.test.orderservice.entity.Order;
import com.test.orderservice.repository.OrderRepository;
import com.test.orderservice.types.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductClient productClient;
    private final OrderRepository repository;

    public Order createOrder(OrderRequest request) {
        ApiResponse<ProductResponse> response = productClient.getProductById(request.getProductId());

        if (response == null || response.getData() == null) {
            throw new RuntimeException("Không tìm thấy thông tin sản phẩm từ Product-Service!");
        }

        ProductResponse product = response.getData();

        if (product.getQuantity() < request.getQuantity()) {
            throw new RuntimeException("Số lượng sản phẩm trong kho không đủ!");
        }

        BigDecimal price = product.getPrice();
        BigDecimal totalPrice = price.multiply(BigDecimal.valueOf(request.getQuantity()));

        Order order = Order.builder()
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .status(EStatus.PENDING)
                .totalPrice(totalPrice)
                .build();

        return repository.save(order);
    }
}

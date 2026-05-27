package com.test.orderservice.dto.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductResponse {
    private Long id;
    private String productName;
    private BigDecimal price;
    private Integer quantity;
}

package com.test.orderservice.entity;

import com.test.orderservice.types.EStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="product_id", nullable = false)
    private Long productId;
    private int quantity;

    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Column(name="total_price")
    private BigDecimal totalPrice;
}

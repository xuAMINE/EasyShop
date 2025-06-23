package com.uup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String name;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String description;
    private String imageUrl;
    private int stock;
    private boolean featured;
    private String color;

    @OneToMany(mappedBy = "product")
    private List<OrderLineItem> orderLineItems;

    @OneToMany(mappedBy = "product")
    private List<ShoppingCartItem> cartItems;
}


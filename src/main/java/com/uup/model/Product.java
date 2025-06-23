package com.uup.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String name;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Lob
    private String description;
    private String color;
    private String imageUrl;
    private int stock;
    private boolean featured;

    @OneToMany(mappedBy = "product")
    private List<OrderLineItem> orderLineItems;

    @OneToMany(mappedBy = "product")
    private List<ShoppingCartItem> cartItems;
}


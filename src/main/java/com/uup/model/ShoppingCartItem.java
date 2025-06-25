package com.uup.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shopping_cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartItem {

    @EmbeddedId
    private ShoppingCartId id;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
}




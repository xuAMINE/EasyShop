package com.uup.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartId implements Serializable {

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "product_id")
    private Integer productId;
}


package com.uup.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ShoppingCartId implements Serializable {
    private Integer user;
    private Integer product;

    // equals() and hashCode() required
}


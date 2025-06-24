package com.uup.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private int productId;
    private String name;
    private BigDecimal price;
    private int categoryId;
    private String description;
    private String color;
    private int stock;
    private String imageUrl;
    private boolean featured;

}


package com.uup.dto;

import com.uup.model.Product;
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

    public static ProductDTO fromEntity(Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .price(product.getPrice())
                .categoryId(product.getCategory() != null ? product.getCategory().getCategoryId() : null)
                .description(product.getDescription())
                .color(product.getColor())
                .stock(product.getStock())
                .imageUrl(product.getImageUrl())
                .featured(product.isFeatured())
                .build();
    }

}


package com.uup.util;

import com.uup.dto.ProductDTO;
import com.uup.model.Product;

public class ProductMapper {
    public static ProductDTO toDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setProductId(product.getProductId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setCategoryId(product.getCategory().getCategoryId());
        dto.setDescription(product.getDescription());
        dto.setColor(product.getColor());
        dto.setStock(product.getStock());
        dto.setImageUrl(product.getImageUrl());
        dto.setFeatured(product.isFeatured());
        return dto;
    }
}

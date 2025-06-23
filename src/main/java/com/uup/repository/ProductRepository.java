package com.uup.repository;

import com.uup.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByFeaturedTrue();
    List<Product> findByCategoryNameIgnoreCase(String categoryName);
}

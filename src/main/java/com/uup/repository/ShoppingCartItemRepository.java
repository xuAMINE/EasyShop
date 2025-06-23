package com.uup.repository;

import com.uup.model.ShoppingCartItem;
import com.uup.model.ShoppingCartId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, ShoppingCartId> {
    List<ShoppingCartItem> findByUserUserId(Integer userId);
}

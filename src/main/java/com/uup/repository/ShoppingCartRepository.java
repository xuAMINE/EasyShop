package com.uup.repository;

import com.uup.model.ShoppingCartId;
import com.uup.model.ShoppingCartItem;
import com.uup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartItem, ShoppingCartId> {
    List<ShoppingCartItem> findByUser(User user);
}

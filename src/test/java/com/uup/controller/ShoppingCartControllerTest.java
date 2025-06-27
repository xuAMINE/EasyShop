package com.uup.controller;

import com.uup.model.*;
import com.uup.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class ShoppingCartControllerTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ShoppingCartController shoppingCartController;

    @Mock
    private Principal principal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(principal.getName()).thenReturn("john");
    }

    @Test
    void testAddProductToCart_FirstTime_AddsNewItem() {
        User user = User.builder().userId(1).username("john").build();
        Product product = Product.builder().productId(2).price(BigDecimal.valueOf(100)).build();

        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));
        when(productRepository.findById(2)).thenReturn(Optional.of(product));
        when(shoppingCartRepository.findById(any())).thenReturn(Optional.empty());

        shoppingCartController.addProductToCart(2, principal);

        verify(shoppingCartRepository).save(any(ShoppingCartItem.class));
    }

    @Test
    void testAddProductToCart_ExistingItem_UpdatesQuantity() {
        User user = User.builder().userId(1).username("john").build();
        Product product = Product.builder().productId(2).price(BigDecimal.valueOf(100)).build();

        ShoppingCartItem item = ShoppingCartItem.builder()
                .id(new ShoppingCartId(1, 2))
                .user(user)
                .product(product)
                .quantity(1)
                .build();

        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));
        when(productRepository.findById(2)).thenReturn(Optional.of(product));
        when(shoppingCartRepository.findById(any())).thenReturn(Optional.of(item));

        shoppingCartController.addProductToCart(2, principal);

        assertThat(item.getQuantity()).isEqualTo(2);
        verify(shoppingCartRepository).save(item);
    }

    @Test
    void testAddProductToCart_UserNotFound() {
        when(userRepository.findByUsername("john")).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> shoppingCartController.addProductToCart(2, principal));
    }

    @Test
    void testAddProductToCart_ProductNotFound() {
        User user = User.builder().userId(1).username("john").build();
        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));
        when(productRepository.findById(2)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> shoppingCartController.addProductToCart(2, principal));
    }

    @Test
    void testClearCart() {
        User user = User.builder().userId(1).username("john").build();
        ShoppingCartItem item = ShoppingCartItem.builder()
                .id(new ShoppingCartId(1, 2))
                .user(user)
                .product(new Product())
                .quantity(1)
                .build();

        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));
        when(shoppingCartRepository.findByUser(user)).thenReturn(List.of(item));

        shoppingCartController.clearCart(principal);

        verify(shoppingCartRepository).deleteAll(List.of(item));
    }

    @Test
    void testClearCart_Empty() {
        User user = User.builder().userId(1).username("john").build();
        when(userRepository.findByUsername("john")).thenReturn(Optional.of(user));
        when(shoppingCartRepository.findByUser(user)).thenReturn(List.of());

        shoppingCartController.clearCart(principal);
        verify(shoppingCartRepository).deleteAll(List.of());
    }
}

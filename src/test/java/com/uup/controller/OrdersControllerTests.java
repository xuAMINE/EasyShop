package com.uup.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.uup.controller.OrdersController;
import com.uup.model.*;
import com.uup.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrdersControllerTests {

    @Mock private ShoppingCartRepository shoppingCartRepository;
    @Mock private UserRepository userRepository;
    @Mock private ProductRepository productRepository;
    @Mock private OrderRepository orderRepository;
    @Mock private OrderLineItemRepository orderLineItemRepository;

    @InjectMocks private OrdersController ordersController;

    private Principal mockPrincipal;
    private User testUser;
    private Product testProduct;
    private ShoppingCartItem testCartItem;
    private Profile profile;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        mockPrincipal = mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("testuser");

        profile = new Profile();
        profile.setAddress("123 St");
        profile.setCity("City");
        profile.setState("CA");
        profile.setZip("99999");

        testUser = new User();
        testUser.setUserId(1);
        testUser.setUsername("testuser");
        testUser.setProfile(profile);

        testProduct = new Product();
        testProduct.setProductId(1);
        testProduct.setName("Product");
        testProduct.setPrice(BigDecimal.valueOf(100));

        testCartItem = new ShoppingCartItem();
        testCartItem.setProduct(testProduct);
        testCartItem.setUser(testUser);
        testCartItem.setQuantity(2);
    }

    @Test
    public void testCheckout_Successful() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(testUser));
        when(shoppingCartRepository.findByUser(testUser)).thenReturn(List.of(testCartItem));

        Order savedOrder = Order.builder()
                .orderId(1)
                .user(testUser)
                .date(LocalDateTime.now())
                .build();

        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        ordersController.checkout(mockPrincipal);

        verify(orderRepository).save(any(Order.class));
        verify(orderLineItemRepository).saveAll(any());
        verify(shoppingCartRepository).deleteAll(any());
    }

    @Test
    public void testCheckout_EmptyCart() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(testUser));
        when(shoppingCartRepository.findByUser(testUser)).thenReturn(List.of());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> ordersController.checkout(mockPrincipal));

        assertEquals(400, ex.getStatusCode().value());
        assertEquals("Shopping cart is empty", ex.getReason());
    }

    @Test
    public void testCheckout_UserNotFound() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class,
                () -> ordersController.checkout(mockPrincipal));
    }

    // Add more if needed to test null profile, invalid product, etc.
}
package com.uup.controller;

import com.uup.model.*;
import com.uup.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;

    @PostMapping
    public void checkout(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)
        );

        List<ShoppingCartItem> cartItems = shoppingCartRepository.findByUser(user);

        if (cartItems.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Shopping cart is empty");
        }

        Order orderToSave = Order.builder()
                .user(user)
                .date(LocalDateTime.now())
                .address(user.getProfile().getAddress())
                .city(user.getProfile().getCity())
                .state(user.getProfile().getState())
                .zip(user.getProfile().getZip())
                .shippingAmount(BigDecimal.valueOf(10))
                .build();

        Order savedOrder = orderRepository.save(orderToSave);

        List<OrderLineItem> lineItems = cartItems.stream().map(item ->
                OrderLineItem.builder()
                        .order(savedOrder)
                        .product(item.getProduct())
                        .salesPrice(item.getProduct().getPrice())
                        .quantity(item.getQuantity())
                        .discount(BigDecimal.ZERO)
                        .build()
        ).collect(Collectors.toList());

        orderLineItemRepository.saveAll(lineItems);
        shoppingCartRepository.deleteAll(cartItems);
    }
}

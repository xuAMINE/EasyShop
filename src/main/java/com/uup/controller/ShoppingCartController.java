package com.uup.controller;

import com.uup.dto.ProductDTO;
import com.uup.dto.ShoppingCartDTO;
import com.uup.dto.ShoppingCartItemDTO;
import com.uup.model.Product;
import com.uup.model.ShoppingCartId;
import com.uup.model.ShoppingCartItem;
import com.uup.model.User;
import com.uup.repository.ProductRepository;
import com.uup.repository.ShoppingCartRepository;
import com.uup.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @GetMapping
    public ShoppingCartDTO getCart(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED)
        );

        List<ShoppingCartItem> items = shoppingCartRepository.findByUser(user);

        Map<Integer, ShoppingCartItemDTO> itemMap = new HashMap<>();
        BigDecimal total = BigDecimal.ZERO;

        for (ShoppingCartItem item : items) {
            Product product = item.getProduct();
            BigDecimal lineTotal = product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

            ProductDTO productDTO = ProductDTO.fromEntity(product);
            itemMap.put(product.getProductId(), new ShoppingCartItemDTO(productDTO, item.getQuantity(), 0, lineTotal));
            total = total.add(lineTotal);
        }

        return new ShoppingCartDTO(itemMap, total);
    }

    @PostMapping("/products/{productId}")
    public void addProductToCart(@PathVariable Integer productId, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        Product product = productRepository.findById(productId).orElseThrow();

        ShoppingCartId id = new ShoppingCartId(user.getUserId(), productId);
        ShoppingCartItem item = shoppingCartRepository.findById(id).orElse(
                ShoppingCartItem.builder()
                        .id(id)
                        .user(user)
                        .product(product)
                        .quantity(0)
                        .build()
        );

        item.setQuantity(item.getQuantity() + 1);
        shoppingCartRepository.save(item);
    }

    @PutMapping("/products/{productId}")
    public void updateProductQuantity(@PathVariable Integer productId, @RequestBody Map<String, Integer> body, Principal principal) {
        int quantity = body.getOrDefault("quantity", 1);
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        ShoppingCartId id = new ShoppingCartId(user.getUserId(), productId);

        ShoppingCartItem item = shoppingCartRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );

        item.setQuantity(quantity);
        shoppingCartRepository.save(item);
    }

    @DeleteMapping
    public void clearCart(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        List<ShoppingCartItem> items = shoppingCartRepository.findByUser(user);
        shoppingCartRepository.deleteAll(items);
    }
}

package com.uup.controller;

import com.uup.dto.ProductDTO;
import com.uup.model.Product;
import com.uup.repository.ProductRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductsController {

    private final ProductRepository productRepository;

    @GetMapping
    @PreAuthorize("permitAll()")
    public List<ProductDTO> search(
            @RequestParam(name = "cat", required = false) Integer categoryId,
            @RequestParam(name = "minPrice", required = false) BigDecimal minPrice,
            @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice,
            @RequestParam(name = "color", required = false) String color
    ) {
        return productRepository.findAll().stream()
                .filter(p -> categoryId == null || p.getCategory().getCategoryId().equals(categoryId))
                .filter(p -> minPrice == null || p.getPrice().compareTo(minPrice) >= 0)
                .filter(p -> maxPrice == null || p.getPrice().compareTo(maxPrice) <= 0)
                .filter(p -> color == null || p.getColor().equalsIgnoreCase(color))
                .map(p -> new ProductDTO(
                        p.getProductId(),
                        p.getName(),
                        p.getPrice(),
                        p.getCategory().getCategoryId(),
                        p.getDescription(),
                        p.getColor(),
                        p.getStock(),
                        p.getImageUrl(),
                        p.isFeatured()
                ))
                .toList();
    }


    @GetMapping("{id}")
    @PreAuthorize("permitAll()")
    public Product getById(@PathVariable int id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Product addProduct(@RequestBody @Valid Product product) {
        return productRepository.save(product);
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Product updateProduct(@PathVariable int id, @RequestBody @Valid Product product) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        product.setProductId(id);
        return productRepository.save(product);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteProduct(@PathVariable int id) {
        if (!productRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
    }
}

// File: com.uup.repository.RepositoryTests.java

package com.uup.repository;

import com.uup.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Should find user by username")
    void findByUsername_success() {
        User user = User.builder().username("testuser").hashedPassword("pass").role("ROLE_USER").build();
        user = userRepository.save(user);

        Optional<User> found = userRepository.findByUsername("testuser");
        assertThat(found).isPresent();
        assertThat(found.get().getUsername()).isEqualTo("testuser");
    }

    @Test
    @DisplayName("Should find profile by user")
    void findProfileByUser_success() {
        User user = userRepository.save(User.builder().username("john").hashedPassword("pass").role("ROLE_USER").build());
        Profile profile = profileRepository.save(Profile.builder().user(user).firstName("John").lastName("Doe").build());

        Optional<Profile> found = profileRepository.findByUser(user);
        assertThat(found).isPresent();
        assertThat(found.get().getFirstName()).isEqualTo("John");
    }

    @Test
    @DisplayName("Should find products by category")
    void findProductsByCategory_success() {
        Category category = categoryRepository.save(Category.builder().name("Electronics").description("Devices").build());
        Product product = productRepository.save(Product.builder()
                .name("Smartphone")
                .price(BigDecimal.valueOf(699.99))
                .stock(10)
                .category(category)
                .build());

        var products = productRepository.findByCategory_CategoryId(category.getCategoryId());
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getName()).isEqualTo("Smartphone");
    }
}

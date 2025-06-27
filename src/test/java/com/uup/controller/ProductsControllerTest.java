package com.uup.controller;

import com.uup.model.Category;
import com.uup.model.Product;
import com.uup.model.Profile;
import com.uup.model.User;
import com.uup.repository.CategoryRepository;
import com.uup.repository.ProductRepository;
import com.uup.repository.ProfileRepository;
import com.uup.repository.UserRepository;
import com.uup.security.jwt.TokenProvider;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ProductsController.class, ProfileController.class})
@AutoConfigureMockMvc(addFilters = false)
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private TokenProvider tokenProvider;

    @MockBean
    private ProfileRepository profileRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryRepository categoryRepository;


    @Test
    void getAllProducts_success() throws Exception {
        Category mockCategory = Category.builder().categoryId(1).name("Electronics").build();

        when(productRepository.findAll()).thenReturn(List.of(
                Product.builder().productId(1).name("Phone").price(BigDecimal.valueOf(500)).category(mockCategory).build(),
                Product.builder().productId(2).name("Laptop").price(BigDecimal.valueOf(1200)).category(mockCategory).build()
        ));


        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }

    @Test
    void getProfile_success() throws Exception {
        User mockUser = new User();
        mockUser.setUserId(1);
        mockUser.setUsername("john");

        Profile profile = Profile.builder().user(mockUser).firstName("John").lastName("Doe").build();

        when(userRepository.findByUsername("john")).thenReturn(Optional.of(mockUser));
        when(profileRepository.findByUser(mockUser)).thenReturn(Optional.of(profile));

        mockMvc.perform(get("/profile").principal(() -> "john"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void updateProfile_success() throws Exception {
        User mockUser = new User();
        mockUser.setUserId(1);
        mockUser.setUsername("john");

        Profile existing = Profile.builder().user(mockUser).firstName("John").build();

        when(userRepository.findByUsername("john")).thenReturn(Optional.of(mockUser));
        when(profileRepository.findByUser(mockUser)).thenReturn(Optional.of(existing));

        mockMvc.perform(put("/profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"firstName\": \"Johnny\"}")
                        .principal(() -> "john"))
                .andExpect(status().isOk());
    }
}

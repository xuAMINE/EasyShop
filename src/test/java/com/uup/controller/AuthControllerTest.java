package com.uup.controller;

import com.uup.model.User;
import com.uup.repository.ProfileRepository;
import com.uup.repository.UserRepository;
import com.uup.security.jwt.TokenProvider;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {

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

    @Test
    void registerUser_success() throws Exception {
        Mockito.when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode("password")).thenReturn("hashed");

        User savedUser = new User();
        savedUser.setUserId(1);
        savedUser.setUsername("newuser");
        savedUser.setRole("ROLE_USER");
        savedUser.setHashedPassword("hashed");

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"newuser\", \"password\": \"password\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void registerUser_existingUsername() throws Exception {
        Mockito.when(userRepository.findByUsername("user")).thenReturn(Optional.of(new User()));

        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"user\", \"password\": \"password\"}"))
                .andExpect(status().isBadRequest());
    }
}

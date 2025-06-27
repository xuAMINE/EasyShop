package com.uup.controller;

import com.uup.dto.ProfileDTO;
import com.uup.model.Profile;
import com.uup.model.User;
import com.uup.repository.ProfileRepository;
import com.uup.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class ProfileController {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;

    @GetMapping
    public ProfileDTO getProfile(Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        Profile profile = profileRepository.findByUser(user).orElseThrow();
        return ProfileDTO.fromEntity(profile);
    }

    @PutMapping
    public void updateProfile(@RequestBody @Valid ProfileDTO profileDTO, Principal principal) {
        User user = userRepository.findByUsername(principal.getName()).orElseThrow();
        Profile profile = profileRepository.findByUser(user).orElseThrow();

        profileDTO.updateEntity(profile);
        profileRepository.save(profile);
    }

}

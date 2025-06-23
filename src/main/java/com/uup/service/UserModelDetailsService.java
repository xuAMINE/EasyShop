package com.uup.service;

import com.uup.repository.UserRepository;
import com.uup.security.exception.UserNotActivatedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserModelDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> {
                    if (!"ROLE_USER".equals(user.getRole()) && !"ROLE_ADMIN".equals(user.getRole())) {
                        throw new UserNotActivatedException("User role not recognized");
                    }
                    return new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            user.getHashedPassword(),
                            List.of(new SimpleGrantedAuthority(user.getRole()))
                    );
                })
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}


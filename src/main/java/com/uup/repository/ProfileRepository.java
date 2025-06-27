package com.uup.repository;

import com.uup.model.Profile;
import com.uup.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Optional<Profile> findByUser(User user);

}

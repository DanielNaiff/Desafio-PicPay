package com.danielnaiff.backend.repository;

import com.danielnaiff.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> existsByEmail();
    Optional<User> existsByDocument();
}

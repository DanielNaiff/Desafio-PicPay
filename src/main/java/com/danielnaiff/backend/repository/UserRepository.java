package com.danielnaiff.backend.repository;

import com.danielnaiff.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package com.danielnaiff.backend.entity.DTO;

import com.danielnaiff.backend.entity.UserType;

public record UserResponseDTO(
        String name,
        String document,
        String email,
        String password,
        UserType userType
) {
}

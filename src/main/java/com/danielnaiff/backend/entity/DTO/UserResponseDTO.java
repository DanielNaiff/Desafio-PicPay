package com.danielnaiff.backend.entity.DTO;

import com.danielnaiff.backend.entity.User;
import com.danielnaiff.backend.entity.UserType;

public record UserResponseDTO(
        String name,
        String document,
        String email,
        String password,
        UserType userType
) {

    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getName(),
                user.getDocument(),
                user.getEmail(),
                user.getPassword(),
                user.getUserType()
        );
}
}

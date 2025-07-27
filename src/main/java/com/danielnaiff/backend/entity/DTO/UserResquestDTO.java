package com.danielnaiff.backend.entity.DTO;

import com.danielnaiff.backend.entity.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserResquestDTO(
        @NotBlank(message = "O nome não pode ser vazio")
        String name,

        @NotBlank(message = "O documento não pode ser vazio")
        String document,

        @NotBlank(message = "O email não pode ser vazio")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "A senha não pode ser vazia")
        String password,

        @NotNull(message = "Voce deve escolher o tipo e usuario")
        UserType userType
        ) {
}

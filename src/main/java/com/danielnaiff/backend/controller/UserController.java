package com.danielnaiff.backend.controller;

import com.danielnaiff.backend.entity.DTO.UserRequestDTO;
import com.danielnaiff.backend.entity.DTO.UserResponseDTO;
import com.danielnaiff.backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO){

        UserResponseDTO response = userService.createUser(userRequestDTO);

        return ResponseEntity.ok(response);
    }
}

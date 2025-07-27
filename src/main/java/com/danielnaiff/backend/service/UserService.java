package com.danielnaiff.backend.service;

import com.danielnaiff.backend.entity.DTO.UserRequestDTO;
import com.danielnaiff.backend.entity.DTO.UserResponseDTO;
import com.danielnaiff.backend.entity.User;
import com.danielnaiff.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO userRequestDto) {
        if(userRepository.existsByDocument(userRequestDto.document())){
            throw new IllegalArgumentException("O documento ja existe");
        }

        if(userRepository.existsByEmail(userRequestDto.email())){
            throw new IllegalArgumentException("O email ja existe");
        }

        User user = User.fromDTO(userRequestDto);
        User savedUser = userRepository.save(user);

        return UserResponseDTO.fromEntity(savedUser);
    }
}

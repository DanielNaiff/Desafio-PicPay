package com.danielnaiff.backend.service;

import com.danielnaiff.backend.Exception.AttributeDoenstExist;
import com.danielnaiff.backend.entity.DTO.UserRequestDTO;
import com.danielnaiff.backend.entity.DTO.UserResponseDTO;
import com.danielnaiff.backend.entity.User;
import com.danielnaiff.backend.entity.UserType;
import com.danielnaiff.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponseDTO createUser(UserRequestDTO userRequestDto) {
        if(userRepository.existsByDocument(userRequestDto.document())){
            throw new AttributeDoenstExist("O documento ja existe");
        }

        if(userRepository.existsByEmail(userRequestDto.email())){
            throw new AttributeDoenstExist("O email ja existe");
        }

        User user = User.fromDTO(userRequestDto);
        User savedUser = userRepository.save(user);

        return UserResponseDTO.fromEntity(savedUser);
    }

    public boolean validateTypeOfUser(User user){
        return user.getUserType() != UserType.MERCHANT;
    }

    public boolean validateAmount(User user, BigDecimal payment) {
        return user.getBalance().compareTo(payment) >= 0;
    }

    public User findById(Long id){

        return userRepository.findById(id).orElseThrow(() -> new AttributeDoenstExist("Usuario nao existe para fazer transferecia"));
    }
}

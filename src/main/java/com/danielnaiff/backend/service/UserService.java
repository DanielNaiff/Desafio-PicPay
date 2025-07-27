package com.danielnaiff.backend.service;

import com.danielnaiff.backend.Exception.AttributeDoenstExist;
import com.danielnaiff.backend.Exception.TranferNotValidated;
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

    public UserResponseDTO createUser(UserRequestDTO userRequestDto) throws Exception {
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

    public void validateUserAndAmount(User user, BigDecimal payment) throws Exception {
        if (user.getUserType() == UserType.MERCHANT) {
            throw new TranferNotValidated("Usuários do tipo MERCHANT não são permitidos.");
        }

        if (user.getBalance().compareTo(payment) < 0) {
            throw new TranferNotValidated(("Saldo insuficiente para o pagamento."));
        }
    }


    public User findById(Long id) throws Exception{
        return userRepository.findById(id).orElseThrow(() -> new AttributeDoenstExist("Usuario nao existe para fazer transferecia"));
    }

    public void saveUser(User user){
        userRepository.save(user);
    }
}

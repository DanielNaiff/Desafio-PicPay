package com.danielnaiff.backend.entity;


import com.danielnaiff.backend.entity.DTO.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String document;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "balance")
    private BigDecimal balance = BigDecimal.ZERO;

    @Column(nullable = false)
    private UserType userType;

    public static User fromDTO(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setDocument(dto.document());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setUserType(dto.userType());
        return user;
    }
}

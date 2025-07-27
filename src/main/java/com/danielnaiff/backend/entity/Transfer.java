package com.danielnaiff.backend.entity;


import com.danielnaiff.backend.entity.DTO.TranferRequestDTO;
import com.danielnaiff.backend.entity.DTO.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tb_transfer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_sender_id")
    private User payer;

    @ManyToOne
    @JoinColumn(name = "user_receiver_id")
    private User payee;

    @Column(name = "value")
    private BigDecimal value;

}

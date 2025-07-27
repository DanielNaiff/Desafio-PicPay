package com.danielnaiff.backend.entity.DTO;

import java.math.BigDecimal;

public record TranferRequestDTO (BigDecimal value, Long payerId, Long payeeId){
}

package com.eduardo.picpaysimplificado.domain.user;

import java.math.BigDecimal;

public record UserDTO(
        String name,
        String document,
        BigDecimal balance,
        String email,
        String password,
        UserType userType
){
}

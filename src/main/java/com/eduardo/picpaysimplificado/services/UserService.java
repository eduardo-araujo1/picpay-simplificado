package com.eduardo.picpaysimplificado.services;

import com.eduardo.picpaysimplificado.domain.user.User;
import com.eduardo.picpaysimplificado.domain.user.UserDTO;
import com.eduardo.picpaysimplificado.domain.user.UserType;
import com.eduardo.picpaysimplificado.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    public void saveUser(User user){
        this.repository.save(user);
    }
    public User createUser(UserDTO user) {
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("Usuario não encontrado."));
    }

    public boolean validateUser(User payer, BigDecimal amount) throws Exception {

        if (payer.getUserType() == UserType.MERCHANT){
            throw new Exception("Um usuário lojista não pode realizar transações.");
        }

        if (payer.getBalance().compareTo(amount)< 0){
            throw new Exception("Saldo insuficiente.");
        }

        return true;
    }
}

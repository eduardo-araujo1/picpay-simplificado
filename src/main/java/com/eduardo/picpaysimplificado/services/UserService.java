package com.eduardo.picpaysimplificado.services;

import com.eduardo.picpaysimplificado.domain.user.User;
import com.eduardo.picpaysimplificado.domain.user.UserDTO;
import com.eduardo.picpaysimplificado.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository repository;

    private void saveUser(User user){
        this.repository.save(user);
    }
    public User createUser(UserDTO user) {
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;
    }
}

package com.eduardo.picpaysimplificado.controller;

import com.eduardo.picpaysimplificado.domain.user.User;
import com.eduardo.picpaysimplificado.domain.user.UserDTO;
import com.eduardo.picpaysimplificado.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user){
        User newUser = service.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        var users = this.service.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}

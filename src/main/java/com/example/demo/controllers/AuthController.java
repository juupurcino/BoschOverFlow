package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.*;
import com.example.demo.implementations.JwtGenerator;
import com.example.demo.implementations.PassEncoder;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    UserService service;

    @Autowired
    UserRepository repo;

    @Autowired
    PassEncoder encoder;

    @Autowired
    JwtGenerator jwtService;

    @PostMapping 
    public ResponseEntity<String> login(@RequestBody UserLoginData user) {

        if (user.edv() == null && user.password() == null) {
            return new ResponseEntity<>("edv and password are expected", HttpStatus.BAD_REQUEST);
        }
        var users = repo.findByEdv(user.edv());

        if (users.isEmpty()) {
            return new ResponseEntity<>("The user not exists", HttpStatus.UNAUTHORIZED);
        }

        var currentUser = users.get(0);

        if(!encoder.matches(user.password(), currentUser.getPassword())) {
            return new ResponseEntity<>("The password is incorret", HttpStatus.UNAUTHORIZED);
        }

        Token token = new Token(currentUser.getId());

        var jwt = jwtService.get(token);

        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}

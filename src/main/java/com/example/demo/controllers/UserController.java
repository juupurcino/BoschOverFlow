package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserData;
import com.example.demo.dto.UserQuery;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody UserData data) {
        if(!service.validateEmail(data.email()))
        {
            return new ResponseEntity<>("Email inválido", HttpStatus.BAD_REQUEST);
        }
        if (!service.validateName(data.name()))
        {
            return new ResponseEntity<>("Nome inválido", HttpStatus.BAD_REQUEST);
        }
        if(!service.validatePassword(data.password()))
        {
            return new ResponseEntity<>("Senha deve ter no minímo 12 caracteres, letra maiuscula, letra minuscula e número", HttpStatus.BAD_REQUEST);
        }

        service.Register(data);
        return new ResponseEntity<>("Usuário cadastrado", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserData>> getUser(Integer page, Integer size, String name) {
        if(page == null)
        {
            page = 0;
        }
        if(size == null)
        {
            size = 0x7fffffff;
        }
        UserQuery queryUser = new UserQuery(name, page, size);

        return new ResponseEntity<>(service.SearchUser(queryUser), HttpStatus.OK);
    }
}
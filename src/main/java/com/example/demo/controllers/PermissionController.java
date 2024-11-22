package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.*;
import com.example.demo.services.*;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    
    @Autowired
    SpaceService service;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody PermissionData data) {
        return new ResponseEntity<>(service.givePermission(data.idUser(), data.idSpace(), data.permission()), HttpStatus.OK);
    }
}

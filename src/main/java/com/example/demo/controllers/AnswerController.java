package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.*;
import com.example.demo.model.AnswerModel;
import com.example.demo.services.*;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    
    @Autowired
    AnswerService service;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AnswerData data) {

        AnswerModel user = service.createAnswer(data);

        if (user == null) {
            return new ResponseEntity<>("User or question doesnt exists!", HttpStatus.OK);
        }

        return new ResponseEntity<>("Answer registered!", HttpStatus.OK);
    }
}

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

import com.example.demo.dto.SpaceQuery;
import com.example.demo.model.SpaceModel;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.services.SpaceService;

@RestController
@RequestMapping("/spaces")
public class SpaceController {
    
    @Autowired
    SpaceService service;

    @Autowired
    SpaceRepository spaceRep;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody String name) {
        
        List<SpaceModel> spaces = spaceRep.findAll();

        for (SpaceModel currenteSpace : spaces) {
            if (currenteSpace.getName().contentEquals(name)) {
                return new ResponseEntity<>("this name already exists!", HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>(service.createSpace(name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<SpaceModel>> getSpace(int page, int size, String name) {
        SpaceQuery querySpace = new SpaceQuery(name, page, size);

        return new ResponseEntity<>(service.searchSpace(querySpace), HttpStatus.OK);
    }
   
    // @DeleteMapping
    // public ResponseEntity<String> delete(Long id) {

    //     Optional<SpaceModel> space = spaceRep.findById(id);
    //     if(!space.isPresent()){return new ResponseEntity<>("id doesnt exists", HttpStatus.BAD_REQUEST);}
        
    //     spaceRep.deleteById(id);

    //     return new ResponseEntity<>("Space deleted!", HttpStatus.OK);
    // }
}

package com.example.demo.dto;

public record UserQuery (
    String name, 
    Integer page,
    Integer size
){}

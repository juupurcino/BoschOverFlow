package com.example.demo.dto;

public record NewQuestionData (
    Token token,
    String title,
    String text,
    Long idUser,
    Long idSpace
){}

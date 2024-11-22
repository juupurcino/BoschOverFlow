package com.example.demo.dto;

public record QuestionData (
    String title,
    String text,
    Long idUser,
    Long idSpace
){}

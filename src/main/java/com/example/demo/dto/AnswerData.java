package com.example.demo.dto;

public record AnswerData (
    String text,
    Long idQuestion,
    Long idUser
){}

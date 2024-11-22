package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.AnswerModel;

public interface AnswerRepository extends JpaRepository<AnswerModel, Long> {
    
}
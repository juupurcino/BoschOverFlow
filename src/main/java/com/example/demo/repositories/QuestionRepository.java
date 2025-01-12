package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.QuestionModel;

public interface QuestionRepository extends JpaRepository<QuestionModel, Long> {
    List<QuestionModel> findByTitle(String title);
}

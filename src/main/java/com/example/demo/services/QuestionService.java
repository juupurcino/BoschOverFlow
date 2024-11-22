package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.QuestionData;
import com.example.demo.dto.QuestionQuery;
import com.example.demo.model.QuestionModel;

public interface QuestionService
{
    QuestionModel createQuestion(QuestionData data);
    String deleteQuestion (Long questionId);
    List<QuestionModel> searchQuestion(QuestionQuery query);
    QuestionModel editQuestion(Long questionId, QuestionData data);
}

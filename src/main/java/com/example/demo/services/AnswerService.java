package com.example.demo.services;

import com.example.demo.dto.AnswerData;
import com.example.demo.model.AnswerModel;

public interface AnswerService {
    AnswerModel createAnswer(AnswerData data);
    String deleteAnswer(Long answerId);
    AnswerModel editAnswer(Long answerId, AnswerData data);
}

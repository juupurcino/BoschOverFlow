package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.example.demo.implementations.*;
import com.example.demo.services.*;

@Configuration
public class DependencyConfiguration
{
    @Bean
    @Scope("singleton")
    public UserService userService()
    {
        return new DefUserService();
    }

    @Bean
    @Scope("singleton")
    public QuestionService questionService()
    {
        return new DefQuestionService();
    }

    @Bean
    @Scope("singleton")
    public SpaceService spaceService()
    {
        return new DefSpaceService();
    }

    @Bean
    @Scope("singleton")
    public AnswerService answerService()
    {
        return new DefAnswerService();
    }

    @Bean
    @Scope("singleton")
    public JwtGenerator jwtGenerator()
    {
        return new JwtGenerator();
    }

    @Bean
    @Scope("singleton")
    public PassEncoder passEncoder()
    {
        return new PassEncoder();
    }
}
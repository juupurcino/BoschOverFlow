package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "UserData")
public class UserModel {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    public Long edv;

    @Column
    public String name;

    @Column
    public String email;

    @Column
    public String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<PermissionModel> Permissions;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<QuestionModel> Questions;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<AnswerModel> Answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEdv() {
        return edv;
    }

    public void setEdv(Long edv) {
        this.edv = edv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<PermissionModel> getPermissions() {
        return Permissions;
    }

    public void setPermissions(List<PermissionModel> permissions) {
        Permissions = permissions;
    }

    public List<QuestionModel> getQuestions() {
        return Questions;
    }

    public void setQuestions(List<QuestionModel> questions) {
        Questions = questions;
    }

    public List<AnswerModel> getAnswer() {
        return Answer;
    }

    public void setAnswer(List<AnswerModel> answer) {
        Answer = answer;
    }


}

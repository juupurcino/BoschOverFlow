package com.example.demo.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Question")
public class QuestionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column 
    private String text;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;

    @ManyToOne
    @JoinColumn(name = "spaceId")
    private SpaceModel space;

    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private List<AnswerModel> Answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public SpaceModel getSpace() {
        return space;
    }

    public void setSpace(SpaceModel space) {
        this.space = space;
    }
}

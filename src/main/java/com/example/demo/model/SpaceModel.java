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
@Table(name = "Space")
public class SpaceModel {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String name;
    
    @OneToMany(mappedBy = "space", orphanRemoval = true)
    private List<PermissionModel> Permissions;
    
    
    @OneToMany(mappedBy = "space", orphanRemoval = true)
    private List<QuestionModel> Questions;
    
    
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    
    public List<QuestionModel> getQuestions() {
        return Questions;
    }
    
    public void setQuestions(List<QuestionModel> Questions) {
        this.Questions = Questions;
    }

    public List<PermissionModel> getPermissions() {
        return Permissions;
    }
    
    public void setPermissions(List<PermissionModel> permissions) {
        Permissions = permissions;
    }
}

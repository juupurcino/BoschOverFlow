package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AuthData;
import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repositories.QuestionRepository;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.QuestionService;


@RestController
@RequestMapping("/question")
public class QuestionController
{
    @Autowired
    QuestionRepository QuestionRep;
    @Autowired
    QuestionService QuestionServ;
    @Autowired
    SpaceRepository SpaceRep;
    @Autowired
    UserRepository UserRep;

    @GetMapping("/{space}")
    public ResponseEntity<Object> GetBySpace(@PathVariable String space, Integer page, Integer size)
    {
        List<SpaceModel> Result = SpaceRep.findByName(space);
        if(Result.size() != 0)
        {
            List<QuestionModel> Questions;
            if(page != null)
            {
                if(size == null)
                {
                    Questions = QuestionServ.searchQuestion(new QuestionQuery(Result.get(0).getId(), page, 10));
                }else
                {
                    Questions = QuestionServ.searchQuestion(new QuestionQuery(Result.get(0).getId(), page, size));
                }
            }else
            {
                Questions = QuestionServ.searchQuestion(new QuestionQuery(Result.get(0).getId(), 0, 0x7fffffff));
            }

            List<QuestionData> Res = new ArrayList<>();
            for (QuestionModel ques : Questions) {
                
                Res.add(new QuestionData(ques.getTitle(), ques.getText(), ques.getUser().getId(), ques.getSpace().getId()));
            }

            return new ResponseEntity<>(Res, HttpStatus.OK);
        }
        return GetByID(Long.parseLong(space));
    }

    public ResponseEntity<Object> GetByID(@PathVariable Long id)
    {
        Optional<QuestionModel> Question = QuestionRep.findById(id);

        if(Question.isPresent())
        {
            QuestionData dataQues = new QuestionData(Question.get().getTitle(), Question.get().getText(), Question.get().getUser().getId(), Question.get().getSpace().getId());
            return new ResponseEntity<>(dataQues, HttpStatus.OK);
        }else
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> Delete(@PathVariable Long id, @RequestBody AuthData data)
    {
        if(data.token() == null)
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        Optional<QuestionModel> Question = QuestionRep.findById(id);

        if(!Question.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        boolean Admin = false;

        List<PermissionModel> Permissions = Question.get().getSpace().getPermissions();
        for(int i = 0; i < Permissions.size(); ++i)
        {
            if(Permissions.get(i).getUser().getId() == data.token().userId())
            {
                if(Permissions.get(i).getPermission() == 1)
                {
                    Admin = true;
                }
            }
        }

        if(Admin || Question.get().getUser().getId() == data.token().userId())
        {
            QuestionRep.deleteById(id);
            return new ResponseEntity<>("Removido com sucesso", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PostMapping
    public ResponseEntity<String> NewQuestion(@RequestBody NewQuestionData data)
    {
        if(data.token() == null)
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Optional<UserModel> User = UserRep.findById(data.token().userId());

        if(!User.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        
        Optional<SpaceModel> Space = SpaceRep.findById(data.idSpace());
        if(!Space.isPresent())
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<PermissionModel> Permissions = Space.get().getPermissions();
        for(int i = 0; i < Permissions.size(); ++i)
        {
            if(Permissions.get(i).getUser().getId() == data.token().userId())
            {
                QuestionModel Question = new QuestionModel();
                Question.setSpace(Space.get());
                Question.setUser(User.get());
                Question.setText(data.text());
                Question.setTitle(data.title());
                QuestionRep.save(Question);
                return new ResponseEntity<>("Inserido", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
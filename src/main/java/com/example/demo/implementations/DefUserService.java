package com.example.demo.implementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;

import com.example.demo.dto.UserData;
import com.example.demo.dto.UserLoginData;
import com.example.demo.dto.UserQuery;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;

public class DefUserService implements UserService
{
    @Autowired
    UserRepository UserRep;
    
    @Autowired
    PassEncoder encoder;

    @Override
    public UserModel Login(UserLoginData data)
    {
        List<UserModel> Results = UserRep.findByEdv(data.edv());
        if(Results.isEmpty())
        {
            return null;
        }
        UserModel Usr = Results.get(0);
        if(Usr.password.contentEquals(data.password()))
        {
            return Usr;
        }
        return null;
    }

    @Override
    public String Register(UserData data)
    {
        UserModel Usr = new UserModel();
        Usr.setEdv(data.edv());
        Usr.setName(data.name());
        Usr.setEmail(data.email());
        Usr.setPassword(encoder.encode(data.password()));
        UserRep.save(Usr);
        return "OK";
    }

    @Override
    public List<UserData> SearchUser(UserQuery query)
    {
        UserModel User = new UserModel();
        User.setName(query.name());

        var Results = UserRep.findAll(Example.of(User),PageRequest.of(query.page(), query.size()));
        List<UserData> Ret = new ArrayList<>();
        Results.get().forEach((item) -> 
        {
            UserData Data = new UserData
            (
                item.getName(),
                item.getEmail(),
                item.getEdv(),
                item.getPassword()
            );
            Ret.add(Data);
        });
        return Ret;
    }

    @Override
    public Boolean validateName(String name)
    {
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) >= 48 && name.charAt(i) <= 57 && name.charAt(i) >= 65 && name.charAt(i) <= 90 && name.charAt(i) >= 97 && name.charAt(i) <= 122 && name.charAt(i) > 127 && name.charAt(i) < 32) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Boolean validateEmail(String email)
    {
        List<UserModel> users = UserRep.findAll();

        int at = email.indexOf("@");

        if (at > 0 && at != email.length()-1)
        {
            for (int i = 0; i < users.size(); i++)
            {
                if (users.get(i).email.contentEquals(email))
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Boolean validatePassword(String password)
    {
        boolean minuscula = false;
        boolean maiscula = false;
        boolean numero = false;
        
        for (int i = 0; i < password.length(); i++) {
            if (password.charAt(i) >= 48 && password.charAt(i) <= 57) {
                numero = true;
            }
            
            if (password.charAt(i) >= 65 && password.charAt(i) <= 90) {
                maiscula = true;
            }
            
            if (password.charAt(i) >= 97 && password.charAt(i) <= 122) {
                minuscula = true;
            }
        }

        if (!numero || !maiscula || !minuscula || password.length() < 12) {
            return false;
        } else {
            return true;
        }
    }
    
}
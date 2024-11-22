package com.example.demo.services;


import java.util.List;

import com.example.demo.dto.UserData;
import com.example.demo.dto.UserLoginData;
import com.example.demo.dto.UserQuery;
import com.example.demo.model.UserModel;

public interface UserService {
    UserModel Login(UserLoginData data);
    
    String Register(UserData data);

    List<UserData> SearchUser(UserQuery query);

    Boolean validateName(String name);

    Boolean validateEmail(String email);

    Boolean validatePassword(String password);
}

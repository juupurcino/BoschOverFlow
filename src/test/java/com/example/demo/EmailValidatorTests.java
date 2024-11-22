package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.services.UserService;


@SpringBootTest
public class EmailValidatorTests {
    
    @Autowired 
    UserService service;
    

    @Test
    void validateNoDotCom() {
        assertEquals(service.validateEmail("email@email"), false);
        assertEquals(service.validateEmail("a@a"), false);
        assertEquals(service.validateEmail("email@a."), false);
        assertEquals(service.validateEmail("email@mail.com."), false);
        assertEquals(service.validateEmail("juninho@escola.pr.gov."), false);
    }
    
    @Test
    void validateNoAt() {
        assertEquals(service.validateEmail("email.com"), false);
        assertEquals(service.validateEmail("juninhoescola.pr.gov.br"), false);
        assertEquals(service.validateEmail("email@"), false);
        assertEquals(service.validateEmail("email@.com"), false);
        assertEquals(service.validateEmail("email.@mail.com"), false);
        assertEquals(service.validateEmail("email@mail@mail.com"), false);
    }

    @Test
    void validateNoEmail() {
        assertEquals(service.validateEmail("@mail.com"), false);
        assertEquals(service.validateEmail("@juninhoescola.pr.gov.br"), false);
    }
    
    @Test
    void validateEmail() {
        assertEquals(service.validateEmail("email@mail.com"), true);
        assertEquals(service.validateEmail("a@a.a"), true);
        assertEquals(service.validateEmail("juninho@escola.pr.gov.br"), true);
    }
    
    @Test
    void validateWrongOrderEmail() {
        assertEquals(service.validateEmail("email.mail@com"), false);
        assertEquals(service.validateEmail("email.mail.com@br"), false);
        assertEquals(service.validateEmail("juninho@escola.pr.gov@br"), false);
    }
}

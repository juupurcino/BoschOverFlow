package com.example.demo.services;

public interface EncodePass {
    public String encode(String pass);
    public boolean matches(String pass, String encodePass);
}
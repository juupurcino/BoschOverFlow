package com.example.demo.dto;

public record PermissionData (
    Long idUser,
    Long idSpace,
    int permission
) {}

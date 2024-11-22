package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.SpaceQuery;

import com.example.demo.model.SpaceModel;

public interface SpaceService {
    String createSpace(String name);
    List<SpaceModel> searchSpace(SpaceQuery query);
    String givePermission(Long userId, Long spaceId, int permission);
}
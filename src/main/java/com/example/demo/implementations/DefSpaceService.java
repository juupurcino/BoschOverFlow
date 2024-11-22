package com.example.demo.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.SpaceQuery;
import com.example.demo.model.PermissionModel;
import com.example.demo.model.SpaceModel;
import com.example.demo.model.UserModel;
import com.example.demo.repositories.PermissionRepository;
import com.example.demo.repositories.SpaceRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.SpaceService;

public class DefSpaceService implements SpaceService {
    @Autowired
    SpaceRepository spaceRep;
    
    @Autowired
    PermissionRepository permRep;
    
    @Autowired
    UserRepository userRep;

    @Override
    public String createSpace(String name) {
        SpaceModel space = new SpaceModel();
        space.setName(name);
        spaceRep.save(space);
        return "Espaço cadastrado!";
    }

    @Override
    public List<SpaceModel> searchSpace(SpaceQuery query) {
        var Results = spaceRep.findAll(Pageable.ofSize(query.size()).withPage(query.page())).getContent();
        List<SpaceModel> Ret = new ArrayList<>();
        
        if (Results == null) {
            return Ret;
        } else {
            return Results;
        }
    }

    @Override
    public String givePermission(Long userId, Long spaceId, int permission) {
        PermissionModel newPerm = new PermissionModel();
        
        Optional<UserModel> user = userRep.findById(userId);
        if(!user.isPresent()){return "Este usuário não existe";}
        
        Optional<SpaceModel> space = spaceRep.findById(spaceId);
        if(!space.isPresent()){return "Esta sala não existe";}

        if (permission < 0 || permission > 1) {
            return "Está permissão não é válida";
        }

        newPerm.setUser(user.get());
        newPerm.setSpace(space.get());
        newPerm.setPermission(permission);

        permRep.save(newPerm);
        return "Permissão cadastrada!";
    }
}

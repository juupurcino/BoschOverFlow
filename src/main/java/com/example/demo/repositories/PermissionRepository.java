package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PermissionModel;

public interface PermissionRepository extends JpaRepository<PermissionModel, Long> {
    List<PermissionModel> findByPermission(int permission);
}

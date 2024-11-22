package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
   List<UserModel> findByName(String name);
   List<UserModel> findByEmail(String email);
   List<UserModel> findByEdv(Long edv);
}

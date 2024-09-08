package com.example.demo4.datasource.services;

import com.example.demo4.datasource.User;
import com.example.demo4.datasource.repositories.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getUserById(Integer id) {
        return userRepo.findUserByUserId(id);
    }
}

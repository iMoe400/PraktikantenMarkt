package com.example.demo4.datasource.repositories;

import com.example.demo4.datasource.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findUserByUserId(Integer userId);
}

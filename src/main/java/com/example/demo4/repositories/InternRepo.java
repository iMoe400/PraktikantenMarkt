package com.example.demo4.repositories;

import com.example.demo4.datasource.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternRepo extends JpaRepository<Intern, Integer> {


    Optional<Intern> findByUserId(int userId);
    Optional<Intern> findByUserId(Integer userId);
}

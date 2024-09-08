package com.example.demo4.datasource.repositories;

import com.example.demo4.datasource.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdressRepo extends JpaRepository<Adress, Integer> {

    List<Adress> findAllByCity(String city);
}

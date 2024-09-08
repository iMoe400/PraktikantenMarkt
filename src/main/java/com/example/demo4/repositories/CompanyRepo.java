package com.example.demo4.repositories;

import com.example.demo4.datasource.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepo  extends JpaRepository<Company, Integer>{
    public Optional<Company> findByUserId(Integer id);
    public Company findByCompanyName(String name);
    public List<Company> findByCompanyNameContaining(String name);
}

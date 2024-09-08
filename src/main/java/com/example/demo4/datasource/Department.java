package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "departments", schema = "praktikantenmarkt")
public class Department {
    @Id
    @Column(name = "DepartmentId", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CompanyId")
    private Company company;

    @Column(name = "DepartmentName")
    private String departmentName;

    @Lob
    @Column(name = "Description")
    private String description;

}
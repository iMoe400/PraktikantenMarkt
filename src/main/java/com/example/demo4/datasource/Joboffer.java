package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "joboffers", schema = "praktikantenmarkt")
public class Joboffer {
    @Id
    @Column(name = "JobOfferId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CompanyId")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DepartmentId")
    private Department department;

    @Column(name = "Title", nullable = false, length = 100)
    private String title;

    @Lob
    @Column(name = "Description", nullable = false)
    private String description;

    @Lob
    @Column(name = "Requirements")
    private String requirements;

    @Column(name = "StartDate")
    private LocalDate startDate;

    @Column(name = "EndDate")
    private LocalDate endDate;

    @ColumnDefault("1")
    @Column(name = "IsActive")
    private Boolean isActive;

}
package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "companies", schema = "praktikantenmarkt")
public class Company {
    @Id
    @Column(name = "CompanyId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "UserId")
    private User user;

    @Column(name = "CompanyName", nullable = false, length = 50)
    private String companyName;

    @Lob
    @Column(name = "Description")
    private String description;

    @ColumnDefault("0")
    @Column(name = "LookingForIntern")
    private Boolean lookingForIntern;

}
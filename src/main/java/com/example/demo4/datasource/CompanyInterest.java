package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company_interest", schema = "praktikantenmarkt")
public class CompanyInterest {
    @Id
    @Column(name = "CompanyInterestId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CompanyId")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InterestId")
    private com.example.demo4.datasource.Interest interest;

}
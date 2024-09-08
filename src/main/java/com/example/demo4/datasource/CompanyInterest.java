package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company_interest", schema = "praktikantenmarkt")
public class CompanyInterest {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InterestId")
    private Interest interest;

}
package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "interns_interests", schema = "praktikantenmarkt")
public class InternsInterest {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "InterestId")
    private Interest interest;

}
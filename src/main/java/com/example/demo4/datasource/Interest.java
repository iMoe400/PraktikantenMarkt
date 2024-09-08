package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "interests", schema = "praktikantenmarkt")
public class Interest {
    @Id
    @Column(name = "InterestId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name", nullable = false, length = 50)
    private String name;

    @Lob
    @Column(name = "Description")
    private String description;

}
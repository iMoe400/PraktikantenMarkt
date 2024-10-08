package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "adress", schema = "praktikantenmarkt")
public class Adress {
    @Id
    @Column(name = "AdressId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "City", nullable = false, length = 50)
    private String city;

    @Column(name = "State", length = 50)
    private String state;

    @Column(name = "PostalCode", nullable = false, length = 20)
    private String postalCode;

    @Column(name = "Street", nullable = false, length = 100)
    private String street;

    @Column(name = "HouseNumber", length = 25)
    private String houseNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryId")
    private com.example.demo4.datasource.Country country;

}
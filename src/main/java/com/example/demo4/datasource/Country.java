package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "countries", schema = "praktikantenmarkt")
public class Country {
    @Id
    @Column(name = "CountryId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "CountryCodeAlpha2", nullable = false, length = 2)
    private String countryCodeAlpha2;

    @Column(name = "CountryCodeAlpha3", length = 3)
    private String countryCodeAlpha3;

    @Column(name = "CountryName", nullable = false, length = 100)
    private String countryName;

    @Column(name = "Region", length = 50)
    private String region;

    @Column(name = "SubRegion", length = 50)
    private String subRegion;

}
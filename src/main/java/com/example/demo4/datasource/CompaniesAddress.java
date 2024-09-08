package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "companies_addresses", schema = "praktikantenmarkt")
public class CompaniesAddress {
    @EmbeddedId
    private CompaniesAddressId id;

    @MapsId("companyId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CompanyId", nullable = false)
    private Company company;

    @MapsId("addressId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AddressId", nullable = false)
    private Adress address;

}
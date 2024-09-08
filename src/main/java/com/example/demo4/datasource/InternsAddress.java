package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "interns_addresses", schema = "praktikantenmarkt")
public class InternsAddress {
    @EmbeddedId
    private InternsAddressId id;

    @MapsId("internId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "InternId", nullable = false)
    private Intern intern;

    @MapsId("addressId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "AddressId", nullable = false)
    private Adress address;

}
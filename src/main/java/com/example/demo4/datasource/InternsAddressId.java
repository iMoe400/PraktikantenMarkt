package com.example.demo4.datasource;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class InternsAddressId implements java.io.Serializable {
    private static final long serialVersionUID = 5259670492582690L;
    @Column(name = "InternId", nullable = false)
    private Integer internId;

    @Column(name = "AddressId", nullable = false)
    private Integer addressId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        InternsAddressId entity = (InternsAddressId) o;
        return Objects.equals(this.internId, entity.internId) &&
                Objects.equals(this.addressId, entity.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internId, addressId);
    }

}
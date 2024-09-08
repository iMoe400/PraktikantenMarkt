package com.example.demo4.datasource;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CompaniesAddressId implements java.io.Serializable {

    @Serial
    private static final long serialVersionUID = 3092595865703589440L;

    @Column(name = "CompanyId", nullable = false)
    private Integer companyId;

    @Column(name = "AddressId", nullable = false)
    private Integer addressId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CompaniesAddressId entity = (CompaniesAddressId) o;
        return Objects.equals(this.companyId, entity.companyId) &&
                Objects.equals(this.addressId, entity.addressId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, addressId);
    }

}
package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "praktikantenmarkt")
public class User {
    @Id
    @Column(name = "UserId", nullable = false)
    private Integer UserId;

    @Column(name = "Username", nullable = false, length = 50)
    private String Username;

    @Column(name = "PasswordHash", nullable = false)
    private String PasswordHash;

    @Column(name = "Email", nullable = false, length = 100)
    private String Email;

    @Lob
    @Column(name = "Role", nullable = false)
    private String Role;

    @ColumnDefault("current_timestamp()")
    @Column(name = "CreatedAt", nullable = false)
    private Instant CreatedAt;

    @ColumnDefault("current_timestamp()")
    @Column(name = "UpdatedAt", nullable = false)
    private Instant UpdatedAt;

    @ColumnDefault("1")
    @Column(name = "IsActive")
    private Boolean IsActive;

}
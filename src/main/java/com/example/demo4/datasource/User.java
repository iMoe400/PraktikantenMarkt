package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "praktikantenmarkt")
public class User {
    @Id
    @Column(name = "UserId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Username", nullable = false, length = 50)
    private String username;

    @Column(name = "PasswordHash", nullable = false)
    private String passwordHash;

    @Column(name = "Email", nullable = false, length = 100)
    private String email;

    @Lob
    @Column(name = "Role", nullable = false)
    private String role;

    // @CreationTimestamp setzt den Wert automatisch beim ersten Speichern des Datensatzes
    @CreationTimestamp
    @Column(name = "CreatedAt", nullable = false, updatable = false)
    private Instant createdAt;

    // @UpdateTimestamp setzt den Wert automatisch bei jeder Aktualisierung des Datensatzes
    @UpdateTimestamp
    @Column(name = "UpdatedAt", nullable = false)
    private Instant updatedAt;

    // Standardwert für IsActive setzen
    @ColumnDefault("1")
    @Column(name = "IsActive", nullable = false)
    private Boolean isActive = true;

    public boolean isActive() {
        return isActive;
    }
}

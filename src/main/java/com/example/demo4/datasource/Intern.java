package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "interns", schema = "praktikantenmarkt")
public class Intern {
    @Id
    @Column(name = "InternId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "UserId")
    private User user;

    @Column(name = "FirsName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "DateOfBirth", nullable = false)
    private LocalDate dateOfBirth;

    @Lob
    @Column(name = "ProfileDescription")
    private String profileDescription;

    @ColumnDefault("0")
    @Column(name = "LookingForCompany")
    private Boolean lookingForCompany;

    @Column(name = "ResumeLink")
    private String resumeLink;

}
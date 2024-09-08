package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company_skills", schema = "praktikantenmarkt")
public class CompanySkill {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SkillId")
    private Skill skill;

}
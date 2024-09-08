package com.example.demo4.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "interns_skills", schema = "praktikantenmarkt")
public class InternsSkill {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SkillId")
    private Skill skill;

}
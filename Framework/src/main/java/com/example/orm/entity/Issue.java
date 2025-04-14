package com.example.orm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "issues")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;

    @ManyToOne
    @JoinColumn(name = "project_id")
    public Project project;
}

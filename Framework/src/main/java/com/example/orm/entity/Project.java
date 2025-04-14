package com.example.orm.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String name;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    public List<Issue> issues = new ArrayList<>();

    @ManyToMany(mappedBy = "projects")
    public Set<User> users = new HashSet<>();
}

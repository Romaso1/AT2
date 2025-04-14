package com.example.orm.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String username;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    public UserProfile profile;

    @ManyToMany
    @JoinTable(
        name = "user_project",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    public Set<Project> projects = new HashSet<>();
}

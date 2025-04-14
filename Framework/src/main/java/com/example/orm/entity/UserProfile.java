package com.example.orm.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "profiles")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String bio;

    @OneToOne
    @JoinColumn(name = "user_id")
    public User user;
}

package com.example;

import javax.persistence.*;

@Entity
@Table(name = "hobby")
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hobbyName;

    // Багато Hobby належать одному Hobbyist
    @ManyToOne
    @JoinColumn(name = "hobbyist_id")
    private Hobbyist hobbyist;

    // Конструктори
    public Hobby() {}

    public Hobby(String hobbyName, Hobbyist hobbyist) {
        this.hobbyName = hobbyName;
        this.hobbyist = hobbyist;
    }

    // Геттери та сеттери
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }

    public Hobbyist getHobbyist() {
        return hobbyist;
    }

    public void setHobbyist(Hobbyist hobbyist) {
        this.hobbyist = hobbyist;
    }

    @Override
    public String toString() {
        return hobbyName;
    }
}

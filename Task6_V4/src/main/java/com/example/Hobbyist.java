package com.example;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hobbyist")
public class Hobbyist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Один Hobbyist може мати багато Hobby
    @OneToMany(mappedBy = "hobbyist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Hobby> hobbies;

    // Конструктори
    public Hobbyist() {}

    public Hobbyist(String name) {
        this.name = name;
    }

    // Геттери та сеттери
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Hobby> getHobbies() {
        return hobbies;
    }

    public void setHobbies(Set<Hobby> hobbies) {
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "Hobbyist{id=" + id + ", name='" + name + "', hobbies=" + hobbies + "}";
    }
}

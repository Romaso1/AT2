package com.example;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hobbyist")
public class Hobbyist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // OneToMany: один Hobbyist може мати багато Hobby
    @OneToMany(mappedBy = "hobbyist", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Hobby> hobbies = new HashSet<>();

    // OneToOne: один Hobbyist має один Profile
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    // ManyToMany: один Hobbyist може належати до кількох Club
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "hobbyist_club",
            joinColumns = @JoinColumn(name = "hobbyist_id"),
            inverseJoinColumns = @JoinColumn(name = "club_id")
    )
    private Set<Club> clubs = new HashSet<>();

    public Hobbyist() {}

    public Hobbyist(String name) {
        this.name = name;
    }

    // Геттери та сеттери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Hobby> getHobbies() { return hobbies; }
    public void setHobbies(Set<Hobby> hobbies) { this.hobbies = hobbies; }

    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) { this.profile = profile; }

    public Set<Club> getClubs() { return clubs; }
    public void setClubs(Set<Club> clubs) { this.clubs = clubs; }

    @Override
    public String toString() {
        return "Hobbyist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profile=" + profile +
                ", hobbies=" + hobbies +
                ", clubs=" + clubs +
                '}';
    }
}

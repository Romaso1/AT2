package com.example;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clubName;

    // Інверсна сторона відношення ManyToMany
    @ManyToMany(mappedBy = "clubs")
    private Set<Hobbyist> hobbyists = new HashSet<>();

    public Club() {}

    public Club(String clubName) {
        this.clubName = clubName;
    }

    // Геттери та сеттери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getClubName() { return clubName; }
    public void setClubName(String clubName) { this.clubName = clubName; }

    public Set<Hobbyist> getHobbyists() { return hobbyists; }
    public void setHobbyists(Set<Hobbyist> hobbyists) { this.hobbyists = hobbyists; }

    @Override
    public String toString() {
        return clubName;
    }
}

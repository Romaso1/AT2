package com.example;

import javax.persistence.*;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;
    private String website;

    public Profile() {}

    public Profile(String bio, String website) {
        this.bio = bio;
        this.website = website;
    }

    // Геттери та сеттери
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", bio='" + bio + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}

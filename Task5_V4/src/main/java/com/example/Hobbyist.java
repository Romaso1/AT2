package com.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.util.List;

public class Hobbyist {
    private int id;
    private String name;
    private List<String> hobbies;

    public Hobbyist() {}

    public Hobbyist(int id, String name, List<String> hobbies) {
        this.id = id;
        this.name = name;
        this.hobbies = hobbies;
    }

    // Геттер та сеттер для JSON
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getHobbies() {
        return hobbies;
    }
    @JsonProperty("hobbies")
    public void setHobbies(List<String> hobbies) {
        this.hobbies = hobbies;
    }

    // Метод для XML – відключаємо від JSON обробки
    @JsonIgnore
    @JacksonXmlElementWrapper(localName = "hobbies")
    @JacksonXmlProperty(localName = "hobby")
    public List<String> getXmlHobbies() {
        return hobbies;
    }

    @Override
    public String toString() {
        return "Hobbyist{id=" + id + ", name='" + name + "', hobbies=" + hobbies + "}";
    }
}

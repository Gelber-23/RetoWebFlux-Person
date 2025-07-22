package com.pragma.person.domain.model;

import com.pragma.person.domain.model.web.Bootcamp;

import java.util.List;

public class Person {

    private Long id;
    private String email;
    private String name;
    private int age ;
    private List<Bootcamp> bootcamps;

    public Person() {
    }

    public Person(Long id, String email, String name, int age, List<Bootcamp> bootcamps) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.age = age;
        this.bootcamps = bootcamps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Bootcamp> getBootcamps() {
        return bootcamps;
    }

    public void setBootcamps(List<Bootcamp> bootcamps) {
        this.bootcamps = bootcamps;
    }
}

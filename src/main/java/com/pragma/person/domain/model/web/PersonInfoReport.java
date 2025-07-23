package com.pragma.person.domain.model.web;

import java.util.List;

public class PersonInfoReport {

    private Long personId;
    private String name;
    private String email;
    private int age;
    private List<Long> bootcampIds;

    public PersonInfoReport() {
    }

    public PersonInfoReport(Long personId, String name, String email, int age, List<Long> bootcampIds) {
        this.personId = personId;
        this.name = name;
        this.email = email;
        this.age = age;
        this.bootcampIds = bootcampIds;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Long> getBootcampIds() {
        return bootcampIds;
    }

    public void setBootcampIds(List<Long> bootcampIds) {
        this.bootcampIds = bootcampIds;
    }
}

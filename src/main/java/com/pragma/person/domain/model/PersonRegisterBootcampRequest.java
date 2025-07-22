package com.pragma.person.domain.model;

import java.util.List;

public class PersonRegisterBootcampRequest {
    private Long personId;
    private List<Long> bootcampsId;

    public PersonRegisterBootcampRequest() {
    }

    public PersonRegisterBootcampRequest(Long personId, List<Long> bootcampsId) {
        this.personId = personId;
        this.bootcampsId = bootcampsId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public List<Long> getBootcampsId() {
        return bootcampsId;
    }

    public void setBootcampsId(List<Long> bootcampsId) {
        this.bootcampsId = bootcampsId;
    }
}

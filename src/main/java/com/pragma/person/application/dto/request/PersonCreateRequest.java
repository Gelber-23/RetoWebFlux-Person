package com.pragma.person.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonCreateRequest {

    private String email;
    private String name;
    private int age ;
}

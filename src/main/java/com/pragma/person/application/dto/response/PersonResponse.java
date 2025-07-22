package com.pragma.person.application.dto.response;

import com.pragma.person.domain.model.web.Bootcamp;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class PersonResponse {

    private Long id;
    private String email;
    private String name;
    private int age ;
    private List<BootcampRepsonse> bootcamps;
}

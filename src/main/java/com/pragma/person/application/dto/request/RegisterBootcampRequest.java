package com.pragma.person.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RegisterBootcampRequest {
    private Long personId;
    private List<Long> bootcampsId;
}

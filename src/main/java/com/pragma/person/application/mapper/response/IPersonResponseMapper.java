package com.pragma.person.application.mapper.response;

import com.pragma.person.application.dto.response.PersonResponse;
import com.pragma.person.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IPersonResponseMapper {
    PersonResponse toResponse(Person person);
}

package com.pragma.person.application.mapper.request;

import com.pragma.person.application.dto.request.PersonCreateRequest;
import com.pragma.person.domain.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPersonCreateRequestMapper {
    Person toModel(PersonCreateRequest personCreateRequest);
}

package com.pragma.person.infraestructure.output.jpa.mapper;

import com.pragma.person.domain.model.Person;
import com.pragma.person.infraestructure.output.jpa.entity.PersonEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IPersonEntityMapper {
    Person toModel (PersonEntity personEntity);
    PersonEntity toEntity ( Person person);
}

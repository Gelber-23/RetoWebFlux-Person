package com.pragma.person.application.mapper.request;

import com.pragma.person.application.dto.request.RegisterBootcampRequest;
import com.pragma.person.domain.model.PersonRegisterBootcampRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRegisterBootcampRequestMapper {
    PersonRegisterBootcampRequest toModel (RegisterBootcampRequest request);
}

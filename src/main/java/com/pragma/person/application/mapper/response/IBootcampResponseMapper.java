package com.pragma.person.application.mapper.response;

import com.pragma.person.application.dto.response.BootcampRepsonse;
import com.pragma.person.domain.model.web.Bootcamp;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBootcampResponseMapper {
    BootcampRepsonse toResponse(Bootcamp bootcamp);
}

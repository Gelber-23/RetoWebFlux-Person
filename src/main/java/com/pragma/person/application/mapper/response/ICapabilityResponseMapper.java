package com.pragma.person.application.mapper.response;

import com.pragma.person.application.dto.response.CapabilityResponse;
import com.pragma.person.domain.model.web.Capability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICapabilityResponseMapper {
    CapabilityResponse toResponse (Capability capability);
}

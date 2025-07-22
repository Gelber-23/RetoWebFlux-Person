package com.pragma.person.infraestructure.input.res;

import com.pragma.person.application.dto.request.PersonCreateRequest;
import com.pragma.person.application.dto.request.RegisterBootcampRequest;
import com.pragma.person.application.dto.response.PersonResponse;
import com.pragma.person.application.handler.IPersonHandler;
import com.pragma.person.domain.util.OpenApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
@Tag(name = OpenApiConstants.TITLE_PERSON_REST, description = OpenApiConstants.TITLE_DESCRIPTION_PERSON_REST)
public class PersonRestController {
    private final IPersonHandler personHandler;


    @Operation(
            summary = OpenApiConstants.NEW_PERSON_TITLE
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode =  OpenApiConstants.RESPONSE_CODE_201, description = OpenApiConstants.NEW_PERSON_CREATED_MESSAGE, content = @Content),
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_400, description = OpenApiConstants.VALIDATIONS_ERRORS_MESSAGE, content = @Content)
    })
    @PostMapping("/")
    public Mono<PersonResponse> createPerson (@RequestBody PersonCreateRequest personCreateRequest) {
        return  personHandler.registerPerson(personCreateRequest);
    }

    @Operation(
            summary = OpenApiConstants.REGISTER_BOOTCAMP_TITLE
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode =  OpenApiConstants.RESPONSE_CODE_201, description = OpenApiConstants.REGISTER_BOOTCAMP_MESSAGE, content = @Content),
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_400, description = OpenApiConstants.VALIDATIONS_ERRORS_MESSAGE, content = @Content)
    })
    @PostMapping("/bootcamp")
    public Mono<PersonResponse> registerInBootcamp (@RequestBody RegisterBootcampRequest registerBootcampRequest) {
        return  personHandler.registerInBootcamps(registerBootcampRequest);
    }


    @Operation(summary = OpenApiConstants.GET_PERSON_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode =  OpenApiConstants.RESPONSE_CODE_200, description = OpenApiConstants.GET_PERSON_MESSAGE, content = @Content),
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_404, description = OpenApiConstants.NO_DATA_MESSAGE, content = @Content)
    })
    @GetMapping("/{id}")
    public Mono<PersonResponse> getPersonById(@PathVariable Long id) {
        return personHandler.getByPerson(id);
    }

    @Operation(summary = OpenApiConstants.COUNT_BOOTCAMP_TITLE)
    @ApiResponses(value = {
            @ApiResponse(responseCode =  OpenApiConstants.RESPONSE_CODE_200, description = OpenApiConstants.COUNT_BOOTCAMP_MESSAGE, content = @Content),
            @ApiResponse(responseCode = OpenApiConstants.RESPONSE_CODE_404, description = OpenApiConstants.NO_DATA_MESSAGE, content = @Content)
    })
    @GetMapping("/bootcamp/{id}/count")
    public Mono<Long> countByBootcamp(@PathVariable("id") Long bootcampId) {
        return personHandler
                .countByBootcampId(bootcampId);
    }
}

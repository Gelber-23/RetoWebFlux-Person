package com.pragma.person.domain.usecase;

import com.pragma.person.domain.api.IPersonServicePort;
import com.pragma.person.domain.exception.*;
import com.pragma.person.domain.model.Person;
import com.pragma.person.domain.model.PersonRegisterBootcampRequest;
import com.pragma.person.domain.model.web.Bootcamp;
import com.pragma.person.domain.spi.IPersonPersistencePort;
import com.pragma.person.domain.spi.web.IBootcampClientPort;
import com.pragma.person.domain.util.ExceptionConstans;
import com.pragma.person.domain.util.ValueConstants;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonUseCase implements IPersonServicePort {

    private final IPersonPersistencePort personPersistencePort;
    private final IBootcampClientPort bootcampClientPort;

    public PersonUseCase(IPersonPersistencePort personPersistencePort, IBootcampClientPort bootcampClientPort) {
        this.personPersistencePort = personPersistencePort;
        this.bootcampClientPort = bootcampClientPort;
    }

    @Override
    public Mono<Person> registerInBootcamps(PersonRegisterBootcampRequest personRegisterBootcampRequest) {
        Long personId = personRegisterBootcampRequest.getPersonId();
        List<Long> incomingIds = personRegisterBootcampRequest.getBootcampsId();

        return validateRequestBootcamps(personRegisterBootcampRequest)
                .then(validatePersonExists(personId))
                // En lugar de zipWith(count…), primero obtenemos la lista actual:
                .then(fetchExistingBootcamps(personId))
                .flatMap(existingList -> {
                    int currentCount = existingList.size();
                    int incomingCount = incomingIds.size();

                    // 1) Validar máximo permitido:
                    return checkMaxAllowed(currentCount, incomingCount)
                            // 2) Obtener lista de nuevos bootcamps a registrar:
                            .then(fetchNewBootcamps(incomingIds))
                            // 3) Juntar ambas listas para validaciones posteriores:
                            .map(newList -> Tuples.of(existingList, newList));
                })
                // 4) Validar duplicados
                .flatMap(tuple -> verifyNoDuplicates(tuple.getT1(), tuple.getT2()).thenReturn(tuple))
                // 5) Validar solapamientos
                .flatMap(tuple -> verifyNoOverlap(tuple.getT1(), tuple.getT2()).thenReturn(tuple))
                // 6) Finalmente, persistir y devolver persona con bootcamps
                .flatMap(tuple -> personPersistencePort
                        .registerInBootcamps(personId, incomingIds)
                );
    }
    @Override
    public Mono<Person> registerPerson(Person person) {
        return validateDataPerson(person)
                .flatMap(personPersistencePort::save);
    }

    @Override
    public Mono<Person> getByPersonId(Long personId) {
        return personPersistencePort.getByPersonID(personId);
    }

    @Override
    public Mono<Long> countByBootcampId(Long bootcampId) {
        return personPersistencePort.countByBootcampId(bootcampId);
    }


    private Mono<Person> validateDataPerson(Person person) {

        List<String> errors = new ArrayList<>();
        if (person.getName() == null || person.getName().isBlank()) {
            errors.add(ExceptionConstans.PERSON_NAME_REQUIRED);
        }

        String mail = person.getEmail();
        if (mail == null || mail.isBlank() || !mail.matches(ValueConstants.EMAIL_REGEX)) {
            errors.add(ExceptionConstans.PERSON_EMAIL_REQUIRED);
        }
        if (person.getAge() <= 0) {
            errors.add(ExceptionConstans.PERSON_AGE_REQUIRED);
        }
        if (!errors.isEmpty()) {
            return Mono.error(new InvalidPersonException(String.join("; ", errors)));
        }
        return  Mono.just(person) ;
    }
    private Mono<Void> validateRequestBootcamps(PersonRegisterBootcampRequest personRegisterBootcampRequest) {
        List<String> errors = new ArrayList<>();

        if (personRegisterBootcampRequest.getPersonId() == null) {
            errors.add(ExceptionConstans.PERSON_ID_REQUIRED);

        }
        if (personRegisterBootcampRequest.getBootcampsId() == null || personRegisterBootcampRequest.getBootcampsId() .isEmpty()) {
            errors.add(ExceptionConstans.BOOTCAMPS_ID_REQUIRED);

        }
        if (!errors.isEmpty()) {
            return Mono.error(new InvalidRequestBootcampException(String.join("; ", errors)));
        }
        return  Mono.empty();




    }
    private Mono<Void> validatePersonExists(Long personId) {
        return personPersistencePort.getByPersonID(personId)
                .switchIfEmpty(Mono.error(new PersonNotFoundException(ExceptionConstans.PERSON_NOT_FOUND + personId)))
                .then();
    }

    private Mono<Long> countCurrentBootcamps(Long personId) {
        return personPersistencePort.countByPersonId(personId);
    }

    private Mono<Void> checkMaxAllowed(long current, int incoming) {
        if (current + incoming > ValueConstants.MAX_COUNT_BOOTCAMP) {
            return Mono.error(new MaxBootcampsExceededException(ExceptionConstans.BOOTCAMP_NOT_COUNT_MIN_MAX));
        }
        return Mono.empty();
    }

    private Mono<List<Bootcamp>> fetchExistingBootcamps(Long personId) {
        return personPersistencePort.getByPersonID(personId)
                .map(Person::getBootcamps)
                .flatMapMany(Flux::fromIterable)
                .collectList();
    }

    private Mono<List<Bootcamp>> fetchNewBootcamps(List<Long> ids) {
        return Flux.fromIterable(ids)
                .flatMap(id -> bootcampClientPort.getBootcampById(id)
                        .switchIfEmpty(Mono.error(new BootcampNotFoundException(ExceptionConstans.BOOTCAMP_NOT_FOUND + id))))
                .collectList();
    }

    private Mono<Void> verifyNoDuplicates(List<Bootcamp> existing, List<Bootcamp> incoming) {
        Set<Long> seen = new HashSet<>();

        for (Bootcamp b : incoming) {
            if (!seen.add(b.getId())) {
                return Mono.error(new DuplicateBootcampException(ExceptionConstans.BOOTCAMP_DUPLICATE + b.getId()));
            }
        }

        for (Bootcamp b : incoming) {
            for (Bootcamp e : existing) {
                if (b.getId().equals(e.getId())) {
                    return Mono.error(new DuplicateBootcampException(ExceptionConstans.BOOTCAMP_DUPLICATE + b.getId()));
                }
            }
        }
        return Mono.empty();
    }

    private Mono<Void> verifyNoOverlap(List<Bootcamp> existing, List<Bootcamp> incoming) {
        for (Bootcamp e : existing) {
            LocalDateTime eStart = e.getDate();
            LocalDateTime eEnd   = eStart.plus(e.getDuration());
            for (Bootcamp n : incoming) {
                LocalDateTime nStart = n.getDate();
                LocalDateTime nEnd   = nStart.plus(n.getDuration());
                if (nStart.isBefore(eEnd) && eStart.isBefore(nEnd)) {
                    return Mono.error(new BootcampOverlapException(
                            String.format(ExceptionConstans.BOOTCAMP_OVERLAP, e.getId(), n.getId()))
                    );
                }
            }
        }
        return Mono.empty();
    }

}

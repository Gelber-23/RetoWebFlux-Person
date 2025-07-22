package com.pragma.person.infraestructure.output.jpa.adapter;

import com.pragma.person.domain.model.Person;
import com.pragma.person.domain.spi.IPersonPersistencePort;
import com.pragma.person.domain.spi.web.IBootcampClientPort;
import com.pragma.person.infraestructure.output.jpa.entity.PersonBootcampEntity;
import com.pragma.person.infraestructure.output.jpa.mapper.IPersonEntityMapper;
import com.pragma.person.infraestructure.output.jpa.repository.IPersonBootcampRepository;
import com.pragma.person.infraestructure.output.jpa.repository.IPersonEntityRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
@RequiredArgsConstructor
public class PersonJpaAdapter implements IPersonPersistencePort {

    private final IPersonEntityRepository personEntityRepository;
    private final IPersonBootcampRepository personBootcampRepository;
    private final IPersonEntityMapper personEntityMapper;
    private final IBootcampClientPort bootcampClientPort;


    @Override
    public Mono<Person> registerInBootcamps(Long personId, List<Long> bootcampsId) {
        return Flux.fromIterable(bootcampsId)
                .map(id -> new PersonBootcampEntity(null, personId, id))
                .flatMap(personBootcampRepository::save)
                .then(getByPersonID(personId));
    }

    @Override
    public Mono<Person> save(Person person) {
        return personEntityRepository
                .save(personEntityMapper.toEntity(person))
                .flatMap(saved -> getByPersonID(saved.getId()));
    }

    @Override
    public Mono<Long> countByPersonId(Long personId) {
        return personBootcampRepository.countByPeopleId(personId);
    }

    @Override
    public Mono<Person> getByPersonID(Long personId) {
        return personEntityRepository.findById(personId)
                .map(personEntityMapper::toModel)
                .flatMap(person -> personBootcampRepository.findAllByPeopleId(personId)
                        .map(PersonBootcampEntity::getBootcampId)
                        .flatMap(bootcampClientPort::getBootcampById)
                        .collectList()
                        .map(bootcamps -> {
                            person.setBootcamps(bootcamps);
                            return person;
                        })
                );
    }

    @Override
    public Mono<Long> countByBootcampId(Long bootcampId) {
        return personBootcampRepository
                .findAllByBootcampId(bootcampId)
                .count();
    }
}

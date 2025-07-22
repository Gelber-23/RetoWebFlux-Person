package com.pragma.person.infraestructure.output.jpa.repository;

import com.pragma.person.infraestructure.output.jpa.entity.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IPersonEntityRepository extends ReactiveCrudRepository<PersonEntity,Long> {
}

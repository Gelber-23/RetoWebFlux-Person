package com.pragma.person.infraestructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("people_bootcamp")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonBootcampEntity {
    @Id
    private Long id;
    private Long peopleId;
    private Long bootcampId;
}

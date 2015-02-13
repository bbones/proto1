package org.proto1.repository;

import org.springframework.data.repository.CrudRepository;
import org.proto1.domain.party.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}

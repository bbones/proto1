package org.proto1.services;

import org.proto1.domain.party.Person;
import org.proto1.repository.PersonRepository;

public interface PersonService {

	void setPersonRepository(PersonRepository prep);

	Person getById(Long l);

	Person save(Person person);

	void delete(Long id);

}

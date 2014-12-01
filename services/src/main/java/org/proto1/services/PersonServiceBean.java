package org.proto1.services;

import org.proto1.domain.Person;
import org.proto1.repository.PersonRepository;

public class PersonServiceBean implements PersonService {
	private PersonRepository personRepository;

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
		
	}

	public Person getById(Long id) {
		return personRepository.findOne(id);
	}

	public Person save(Person person) {
		return personRepository.save(person);
	}

	public void delete(Long id) {
		personRepository.delete(id);
	}

}

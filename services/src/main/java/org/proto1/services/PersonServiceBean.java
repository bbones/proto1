package org.proto1.services;

import org.proto1.domain.Person;
import org.proto1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceBean implements PersonService {
	@Autowired
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

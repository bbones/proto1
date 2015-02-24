package org.proto1.services.party;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.Person;
import org.proto1.domain.party.PersonName;
import org.proto1.repository.PersonNameRepository;
import org.proto1.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceBean implements PersonService {
	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonNameRepository personNameRepository;

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

	public List<Map<String, Object>> getPersonList(Long languageId) {
		return personRepository.getListByLanguageId(languageId);
	}

	public List<PersonName> getNamesList(Long personId) {
		return personNameRepository.getByPersonId(personId);
	}


}

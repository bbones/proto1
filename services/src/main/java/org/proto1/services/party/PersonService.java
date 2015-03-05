/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services.party;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.Person;
import org.proto1.domain.party.PersonName;
import org.proto1.repository.PersonRepository;

public interface PersonService {

	void setPersonRepository(PersonRepository prep);

	Person getById(Long l);

	Person save(Person person);

	void delete(Long id);

	List<Map<String, Object>> getPersonList(Long languageId);

	List<PersonName> getNamesList(Long personIdId);

}

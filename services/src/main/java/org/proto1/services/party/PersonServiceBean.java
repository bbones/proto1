/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
package org.proto1.services.party;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.Person;
import org.proto1.domain.party.PersonName;
import org.proto1.repository.party.PersonNameRepository;
import org.proto1.repository.party.PersonRepository;
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

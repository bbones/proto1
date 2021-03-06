/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
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
 * GNU v2 license text in root directory of project
 *******************************************************************************/
package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.party.PersonName;
import org.proto1.domain.party.Person;
import org.proto1.dto.PersonNameDTO;
import org.proto1.dto.PersonDTO;
import org.proto1.services.party.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/persons")
public class PersonController {
	@Autowired
	PersonService personService;
	
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  personListByLanguage(@RequestParam Long languageId) {
		return personService.getPersonList(languageId);
	}
	
	@RequestMapping(value = "{id}/names", method = RequestMethod.GET)
	public @ResponseBody List<PersonNameDTO> getEntepriseNames(@PathVariable Long id) {
		List<PersonNameDTO> enList = new ArrayList<PersonNameDTO>();
		for(PersonName en : personService.getNamesList(id))
			enList.add(mapper.map(en, PersonNameDTO.class));
		return enList;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody PersonDTO submit(PersonDTO personDTO) {
		Person Person = mapper.map(personDTO, Person.class);
		Person = personService.save(Person);
		mapper.map(Person, personDTO);
		return personDTO;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody PersonDTO get(@PathVariable Long id) {
		Person Person = personService.getById(id);
		return mapper.map(Person, PersonDTO.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		personService.delete(id);
	}



}

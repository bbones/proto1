package org.proto1.protofront;

import org.dozer.Mapper;
import org.proto1.domain.party.Person;
import org.proto1.dto.PersonDTO;
import org.proto1.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/person")
public class PersonController {
	@Autowired
	PersonService personService;
	
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value = "submit", method = RequestMethod.POST, produces = "application/json", consumes="application/json" )
	public @ResponseBody PersonDTO submit(@RequestBody final PersonDTO personDTO) {
		Person Person = mapper.map(personDTO, Person.class);
		Person = personService.save(Person);
		mapper.map(Person, personDTO);
		return personDTO;
	}

	@RequestMapping(value = "findByID/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody PersonDTO findByID(@PathVariable String id) {
		Person Person = personService.getById(new Long(id));
		return mapper.map(Person, PersonDTO.class);
	}

	@RequestMapping(value = "deleteByID/{id}", method = RequestMethod.POST, produces = "application/json")
	public void delete(@PathVariable String id) {
		personService.delete(new Long(id));
	}


	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	public RedirectView findByURLID(@PathVariable String id) {
		
		return new RedirectView("/protofront/index.html#Person:"+id);
	}

}

/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import java.util.List;

import org.proto1.domain.party.PersonName;
import org.springframework.data.repository.CrudRepository;

public interface PersonNameRepository extends CrudRepository<PersonName, Long> {
	List<PersonName> getByPersonId(Long personId);

}

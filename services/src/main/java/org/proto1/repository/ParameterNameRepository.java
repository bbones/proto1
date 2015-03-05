/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import java.util.List;

import org.proto1.domain.product.ParameterName;
import org.springframework.data.repository.CrudRepository;

public interface ParameterNameRepository extends CrudRepository<ParameterName, Long> {

	List<ParameterName> getByLanguageId(Long languageId);
	List<ParameterName> getByParameterId(Long parameterId);


}

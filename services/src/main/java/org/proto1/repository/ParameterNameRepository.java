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
package org.proto1.repository;

import java.util.List;

import org.proto1.domain.product.ParameterName;
import org.springframework.data.repository.CrudRepository;

public interface ParameterNameRepository extends CrudRepository<ParameterName, Long> {

	List<ParameterName> getByLanguageId(Long languageId);
	List<ParameterName> getByParameterId(Long parameterId);


}

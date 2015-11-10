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
package org.proto1.services.parameter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.product.Parameter;
import org.proto1.domain.product.ParameterName;

public interface ParameterService {

	Parameter get(Long id);
	
	Parameter getEagerly(Long id);
	
	List<Map<String, Object>> getParameterList(Long languageId);

	List<ParameterName>  getNames(Long parameterId);

	/**
	 * @param parameter
	 * @return
	 */
	Parameter save(Parameter parameter);

	/**
	 * @param parameterName
	 * @return 
	 */
	ParameterName saveParameterName(ParameterName parameterName);

	void deleteParameter(Long parameterId);

	void deleteParameterName(Long parameterNameId);

	Set<UnitOfMeasurement> getAcceptedUOMs(Long id);


}

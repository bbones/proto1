/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services.parameter;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.Parameter;
import org.proto1.domain.product.ParameterName;

public interface ParameterService {

	Parameter get(Long id);
	
	List<Map<String, Object>> getParameterList(Long languageId);

	List<ParameterName> getParameterNamesList(Long languageId);

	List<ParameterName>  getNamesList(Long parameterId);

}

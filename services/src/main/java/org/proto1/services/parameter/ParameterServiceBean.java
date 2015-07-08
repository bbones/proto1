/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services.parameter;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.product.Parameter;
import org.proto1.domain.product.ParameterName;
import org.proto1.repository.ParameterNameRepository;
import org.proto1.repository.ParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterServiceBean implements ParameterService {
	
	@Autowired
	ParameterRepository parameterRepository;
	
	@Autowired
	ParameterNameRepository parameterNameRepository;

	public List<Map<String, Object>> getParameterList(Long languageId) {
		return parameterRepository.getList(languageId);
	}

	public List<ParameterName> getNames(Long parameterId) {
		return parameterNameRepository.getByParameterId(parameterId);
	}

	public Parameter get(Long id) {
		return parameterRepository.findOne(id);
	}

	public void deleteParameter(Long parameterId) {
		parameterRepository.delete(parameterId);
		
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.parameter.ParameterService#save(org.proto1.domain.product.Parameter)
	 */
	public Parameter save(Parameter parameter) {
		return parameterRepository.save(parameter);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.parameter.ParameterService#saveParameterName(org.proto1.domain.product.ParameterName)
	 */
	public ParameterName saveParameterName(ParameterName parameterName) {
		return parameterNameRepository.save(parameterName);
	}

	public void deleteParameterName(Long parameterNameId) {
		parameterNameRepository.delete(parameterNameId);
		
	}

	@Transactional
	public Parameter getEagerly(Long id) {
		return parameterRepository.getEagerly(id);
	}

	@Transactional
	public Set<UnitOfMeasurement> getAcceptedUOMs(Long id) {
		return parameterRepository.getEagerly(id).getAcceptedUOM();
	}

}

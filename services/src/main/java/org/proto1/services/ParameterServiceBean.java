package org.proto1.services;

import java.util.List;

import org.proto1.domain.product.ParameterName;
import org.proto1.repository.ParameterNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterServiceBean implements ParameterService {
	@Autowired
	ParameterNameRepository parameterNameRepository;

	public List<ParameterName> getParameterNamesList(Long languageId) {
		return parameterNameRepository.getByLanguageId(languageId);
	}

}

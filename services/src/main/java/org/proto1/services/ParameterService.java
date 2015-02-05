package org.proto1.services;

import java.util.List;

import org.proto1.domain.product.ParameterName;

public interface ParameterService {

	List<ParameterName> getParameterNamesList(Long languageId);

}

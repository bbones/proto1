package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.ParameterName;

public interface ParameterService {

	List<Map<String, Object>> getParameterList(Long languageId);

	List<ParameterName> getParameterNamesList(Long languageId);

	List<ParameterName>  getNamesList(Long parameterId);

}

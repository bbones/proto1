package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.EnterpriseName;
import org.proto1.repository.EnterpriseRepository;

public interface EnterpriseService {

	void setEnterpriseRepository(EnterpriseRepository erep);

	Enterprise getEnterpriseById(Long id);
	
	Enterprise save(Enterprise isd);

	void delete(Long id);

	List<Map<String, Object>> getEnterpriseList(Long languageId);

	List<EnterpriseName> getNamesList(Long enterpriseId);


}

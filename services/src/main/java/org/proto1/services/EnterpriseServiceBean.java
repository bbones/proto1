package org.proto1.services;

import org.proto1.domain.Enterprise;
import org.proto1.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class EnterpriseServiceBean implements EnterpriseService {
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	public Enterprise getEnterpriseById(long id) {
		return null;
	}

}

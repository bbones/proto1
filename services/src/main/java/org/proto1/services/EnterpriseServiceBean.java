package org.proto1.services;

import org.proto1.domain.party.Enterprise;
import org.proto1.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceBean implements EnterpriseService {
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	public Enterprise getEnterpriseById(Long id) {
		return enterpriseRepository.findOne(id);
	}

	public void setEnterpriseRepository(EnterpriseRepository erep) {
		enterpriseRepository = erep;
	}

	public Enterprise save(Enterprise enterprise) {
		return enterpriseRepository.save(enterprise);
	}

	public void delete(Long id) {
		enterpriseRepository.delete(id);
	}

}

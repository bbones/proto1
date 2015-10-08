/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services.party;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.EnterpriseName;
import org.proto1.repository.party.EnterpriseNameRepository;
import org.proto1.repository.party.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseServiceBean implements EnterpriseService {
	
	@Autowired
	EnterpriseRepository enterpriseRepository;
	
	@Autowired
	EnterpriseNameRepository enterpriseNameRepository;
	
	public Enterprise get(Long id) {
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

	public List<Map<String, Object>> getEnterpriseList(Long languageId) {
		return enterpriseRepository.getListByLanguageId(languageId);
	}

	public List<EnterpriseName> getNamesList(Long enterpriseId) {
		return enterpriseNameRepository.getByEnterpriseId(enterpriseId);
	}

	public Long getEnterpriseListCounter(Long languageId, String searchStr) {
		return enterpriseRepository.getEnterpriseCounter(languageId, searchStr);
	}

	public List<Map<String, Object>> getList(Long languageId, String searchStr,
			Pageable p) {
		return enterpriseRepository.partyList(languageId, searchStr, p);
	}

	public EnterpriseName saveName(EnterpriseName enterpriseName) {
		return enterpriseNameRepository.save(enterpriseName);
	}

	public void deleteName(Long id) {
		enterpriseNameRepository.delete(id);
		
	}

	public Long getEnterpriseListCounter(Long languageId, Enterprise exmpl) {
		return enterpriseRepository.countById(exmpl);
	}

	public List<Map<String, Object>> getList(Long languageId, Enterprise exmpl,
			Pageable p) {
		// TODO Auto-generated method stub
		return enterpriseRepository.findAll(exmpl);
	}

}

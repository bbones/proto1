/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.proto1.domain.Language;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.proto1.repository.LanguageRepository;
import org.proto1.repository.UnitOfMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterDataServiceBean implements MasterDataService {
	@Autowired
	LanguageRepository languageRepository;
	
	@Autowired
	UnitOfMeasurementRepository unitOfMeasurementRepository;
	
	public void setLanguageRepository(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
		
	}

	public List<Language> getLanguageList() {
		
		return (List<Language>) languageRepository.findAll();
	}

	public List<Language> getRequiredLanguageList() {
		return languageRepository.getRequiredLanguageList();
	}

	public LocalizedStringConstant getLocalizedString(String key, Long languageId) {
		return languageRepository.getLocalizedStringConstant(key, languageId);
	}

	public List<LocalizedStringConstant> getRequiredLocalizedStringList(String key) {
		return languageRepository.getRequiredLocalizedStringConstantList(key);
	}

	public List<Map<String, Object>> getUOMList(Long languageId) {
		return unitOfMeasurementRepository.getList(languageId);
	}

}

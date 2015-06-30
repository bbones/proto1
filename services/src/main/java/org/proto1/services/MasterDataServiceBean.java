/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.proto1.domain.Currency;
import org.proto1.domain.Language;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.proto1.repository.CurrencyRepository;
import org.proto1.repository.LanguageRepository;
import org.proto1.repository.party.PartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MasterDataServiceBean implements MasterDataService {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	LanguageRepository languageRepository;
	
	@Autowired
	PartyRepository partyRepository;

	@Autowired
	CurrencyRepository currencyRepository;

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

	public List<Map<String, Object>> getParyList(Long languageId,
			String searchStr, Pageable p) {
		return partyRepository.partyList(languageId, searchStr, p);
	}

	public Long getParyListCounter(Long languageId, String searchStr) {
		return partyRepository.getPartyCounter(languageId, searchStr);
	}

	public List<Currency> getCurrencyList() {
		return (List<Currency>) currencyRepository.findAll();
	}

	public Currency getCurrency(Integer numCode) {
		return currencyRepository.findOne(numCode);
	}

}

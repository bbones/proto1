/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.proto1.domain.Currency;
import org.proto1.domain.Language;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.proto1.repository.LanguageRepository;
import org.springframework.data.domain.Pageable;

public interface MasterDataService {

	void setLanguageRepository(LanguageRepository languageRepository);

	List<Language> getLanguageList();
	
	List<Language> getRequiredLanguageList();
	
	LocalizedStringConstant getLocalizedString(String key, Long languageId);

	List<LocalizedStringConstant> getRequiredLocalizedStringList(String key);

	/**
	 * @param languageId, searchStr
	 * @return
	 */
	List<Map<String, Object>> getParyList(Long languageId, String searchStr, Pageable p);
	
	Long getParyListCounter(Long languageId, String searchStr);
	
	List<Currency> getCurrencyList();

	Currency getCurrency(Integer numCode);

}

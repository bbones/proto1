/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
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

/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services;

import java.util.List;
import org.proto1.domain.Language;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.proto1.repository.LanguageRepository;

public interface MasterDataService {

	void setLanguageRepository(LanguageRepository languageRepository);

	List<Language> getLanguageList();
	
	List<Language> getRequiredLanguageList();
	
	LocalizedStringConstant getLocalizedString(String key, Long languageId);

	List<LocalizedStringConstant> getRequiredLocalizedStringList(String key);

}

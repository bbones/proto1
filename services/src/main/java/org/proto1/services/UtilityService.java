package org.proto1.services;

import org.proto1.domain.Language;

public interface UtilityService {
	
	String getLocalizedString(String key, Language language);

}

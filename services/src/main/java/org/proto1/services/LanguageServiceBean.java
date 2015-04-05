/**
 * LanguageServiceBean.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 3, 2015
 */
package org.proto1.services;

import org.proto1.domain.Language;
import org.proto1.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@Service
public class LanguageServiceBean implements LanguageService {
	
	@Autowired
	LanguageRepository languageRepository;

	/* (non-Javadoc)
	 * @see org.proto1.services.LanguageService#get(java.lang.Long)
	 */
	public Language get(Long languageId) {
		return languageRepository.findOne(languageId);
	}
	

}

/**
 * LanguageService.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 3, 2015
 */
package org.proto1.services;

import org.proto1.domain.Language;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface LanguageService {

	/**
	 * @param languageId
	 * @return
	 */
	Language get(Long languageId);

}

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
package org.proto1.services.party;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.EnterpriseName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnterpriseService {

	Enterprise get(Long id);
	
	Enterprise save(Enterprise isd);

	void delete(Long id);

	List<Map<String, Object>> getEnterpriseList(Long languageId);

	List<EnterpriseName> getNamesList(Long enterpriseId);

	Long getEnterpriseListCounter(Long languageId, String string);

	List<Map<String, Object>> getList(Long languageId, String string, Pageable p);

	EnterpriseName saveName(EnterpriseName enterpriseName);

	void deleteName(Long id);

	String esindex() throws IOException;
	
	List<Map<String, Object>> search(Long languageId, String string, Pageable p);

}

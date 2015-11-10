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
/** Rsk 09.07.2015 */
package org.proto1.services;

import java.util.List;
import java.util.Map;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.proto1.domain.RailwayName;
import org.proto1.repository.RailwayNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RailwayNameServiceBean implements RailwayNameService {
	//protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	RailwayNameRepository railwayNameRepository;
	
	public RailwayName getRailwayName(Long id) {
		return railwayNameRepository.findOne(id);
	}

	public List<Map<String, Object>> getRailwayNameList(Long railwayId) {
		return railwayNameRepository.getList(railwayId);
	}

	public RailwayName save(RailwayName railwayName) {
		//logger.debug("railwayName.getId()=" + railwayName.getId());
		//logger.debug("railwayName.getShortName()=" + railwayName.getShortName());
		//logger.debug("railwayName.getFullName()=" + railwayName.getFullName());
		//logger.debug("railwayName.getVersion()=" + railwayName.getVersion());
		//logger.debug("railwayName.getLanguage()=" + railwayName.getLanguage().getId());
		//logger.debug("railwayName.getRailway()=" + railwayName.getRailway().getId());
		return railwayNameRepository.save(railwayName);
	}
	
	public void delete(Long id) {
		railwayNameRepository.delete(id);
	}

}

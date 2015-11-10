/*******************************************************************************
 * Copyright (C) 2015   Serhiy Romaniuk 
 *
 * mail:rsk@isd.com.ua
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

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.proto1.domain.Railway;
import org.proto1.repository.RailwayRepository;
import org.proto1.specifications.PlainSpecification;
import org.proto1.specifications.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

@Service
public class RailwayServiceBean implements RailwayService {
	//protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	RailwayRepository railwayRepository;
	
	public Railway getRailway(Long id) {
		return railwayRepository.findOne(id);
	}
	
	public Railway getByRailwayCode(Integer railwayCode) {
		return railwayRepository.findByRailwayCode(railwayCode);
	}
	
	public List<Map<String, Object>> getRailwayList() {
		return railwayRepository.getList();
	}

	public Railway save(Railway railway) {
		//logger.debug("railway.getId()=" + railway.getId());
		//logger.debug("railway.getRailwayCode()=" + railway.getRailwayCode());
		//logger.debug("railway.getVersion()=" + railway.getVersion());
		return railwayRepository.save(railway);
	}
	
	public void delete(Long id) {
		railwayRepository.delete(id);
	}
	
	public List<Railway> findAll(Railway criteria) {
		PlainSpecification<Railway> spec1 = new PlainSpecification<Railway>(
				new SearchCriteria("id", "=", criteria.getId()));
		PlainSpecification<Railway> spec2 = new PlainSpecification<Railway>(
				new SearchCriteria("railwayCode", "like",
						criteria.getRailwayCode()));
		return railwayRepository.findAll(Specifications.where(spec1).and(spec2));
	}

}

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
package org.proto1.protofront;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.dozer.Mapper;
import org.proto1.domain.Railway;
import org.proto1.domain.RailwayName;
import org.proto1.dto.RailwayDTO;
import org.proto1.dto.RailwayNameDTO;
import org.proto1.services.RailwayNameService;
import org.proto1.services.RailwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/railways")
public class RailwayController {
	//protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	RailwayService railwayService;
	
	@Autowired
	RailwayNameService railwayNameService;
	
	@Autowired
	Mapper mapper;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getRailwayList() {
		return railwayService.getRailwayList();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody RailwayDTO save(RailwayDTO railwayDTO) {
		Railway railway = mapper.map(railwayDTO, Railway.class);
		railway = railwayService.save(railway);
		mapper.map(railway, railwayDTO);
		return railwayDTO;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		railwayService.delete(id);
	}
	
	/** ---------- methods for Railway Search Form ---------- */
	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	public @ResponseBody List<RailwayDTO> findAll(Railway criteria) {
		List<Railway> railwayList = railwayService.findAll(criteria);
		List<RailwayDTO> railwayDTOList = new ArrayList<RailwayDTO>();
		for (Railway railway : railwayList) {
			railwayDTOList.add((RailwayDTO) mapper.map(railway,
					RailwayDTO.class));
		}
		return railwayDTOList;
	}

	/** ---------- methods for Railway Form ---------- */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody RailwayDTO get(@PathVariable Long id) {
		Railway railway = railwayService.getRailway(new Long(id));
		return mapper.map(railway, RailwayDTO.class);
	}
	
	/** ---------- methods for RailwayNames ---------- */
	@RequestMapping(value = "{railwayId}/names", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getRailwayNameList(@PathVariable Long railwayId) {
		return railwayNameService.getRailwayNameList(railwayId);
	}
	
	@RequestMapping(value = "names", method = RequestMethod.POST)
	public @ResponseBody RailwayNameDTO save(RailwayNameDTO railwayNameDTO) {
		RailwayName railwayName = mapper.map(railwayNameDTO, RailwayName.class);
		
		//Railway railway = railwayService.getRailway(railwayNameDTO.getRailwayId());
		//railwayName.setRailway(railway);
		//Language language = languageService.get(railwayNameDTO.getLanguageId());
		//railwayName.setLanguage(language);
		
		railwayName = railwayNameService.save(railwayName);
		mapper.map(railwayName, railwayNameDTO);
		return railwayNameDTO;
	}
	
	@RequestMapping(value = "names/{id}", method = RequestMethod.DELETE)
	public void deleteName(@PathVariable Long id) {
		railwayNameService.delete(id);
	}
	
}

/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.dto.LanguageDTO;
import org.proto1.services.MasterDataService;
import org.proto1.services.UnitOfMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/masterdata")
public class MasterDataController {
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	MasterDataService masterDataService;
	
	@RequestMapping(value = "languages", method = RequestMethod.GET)
	public @ResponseBody List<LanguageDTO> getLanguageList() {
		List<LanguageDTO> langlist = new ArrayList<LanguageDTO>();
		mapper.map(masterDataService.getLanguageList(), langlist);
		return langlist;
	}

	
}

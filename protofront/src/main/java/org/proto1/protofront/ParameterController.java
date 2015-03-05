/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.proto1.domain.product.ParameterName;
import org.proto1.dto.ParameterNameDTO;
import org.proto1.services.product.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parameter")
public class ParameterController {
	final static Logger logger = Logger.getLogger(ParameterController.class);
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	ParameterService parameterService;
	
	
	@RequestMapping(value = "parameterNamesByLanguageId/{languageId}", method = RequestMethod.GET)
	public List<ParameterNameDTO> getParameterNamesList(@PathVariable Long languageId) {
		List<ParameterNameDTO> result = new ArrayList<ParameterNameDTO>();
		for(ParameterName pn : parameterService.getParameterNamesList(languageId))
			result.add(mapper.map(pn, ParameterNameDTO.class)); 
		
		return result;
		
	}

	@RequestMapping(value = "parameterListByLanguageId/{languageId}", method = RequestMethod.POST)
	public List<Map<String, Object>> getParameterList(@PathVariable Long languageId) {
		return parameterService.getParameterList(languageId);
		
	}
	
	@RequestMapping(value = "names/{id}", method = RequestMethod.POST)
	public @ResponseBody List<ParameterNameDTO> getParameterNames(@PathVariable String id) {
		List<ParameterNameDTO> pnList = new ArrayList<ParameterNameDTO>();
		for(ParameterName pn : parameterService.getNamesList(new Long(id)))
			pnList.add(mapper.map(pn, ParameterNameDTO.class));
		return pnList;
	}

}

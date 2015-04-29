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
import org.proto1.domain.Language;
import org.proto1.domain.product.Parameter;
import org.proto1.domain.product.ParameterName;
import org.proto1.dto.ParameterDTO;
import org.proto1.dto.ParameterNameDTO;
import org.proto1.services.LanguageService;
import org.proto1.services.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parameters")
public class ParameterController {
	final static Logger logger = Logger.getLogger(ParameterController.class);
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	ParameterService parameterService;
	
	@Autowired
	LanguageService languageService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Map<String, Object>> getParameterList(@RequestParam Long languageId) {
		return parameterService.getParameterList(languageId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ParameterDTO getParameterList(@RequestParam Long languageId, ParameterDTO parameterDTO) {
		Parameter parameter = new Parameter();
		if (parameterDTO.getParameterId() != null)
			parameter.setId(parameterDTO.getParameterId());
		parameter.setType(Parameter.Type.valueOf(parameterDTO.getParameterType()));
		parameter.setVersion(parameterDTO.getVersion());
		if (parameter.getParameterNames().size() == 0) {
			Language language = languageService.get(languageId);
			parameter.getParameterNames().add(new ParameterName(parameter, parameterDTO.getParameterName(), language));
		}
		parameter = parameterService.save(parameter);
		parameterDTO.setParameterId(parameter.getId());
		parameterDTO.setVersion(parameter.getVersion());
		
		return parameterDTO;
	}
	
	@RequestMapping(value = "{id}/names", method = RequestMethod.GET)
	public @ResponseBody List<ParameterNameDTO> getParameterNames(@PathVariable String id) {
		List<ParameterNameDTO> pnList = new ArrayList<ParameterNameDTO>();
		for(ParameterName pn : parameterService.getNames(new Long(id)))
			pnList.add(mapper.map(pn, ParameterNameDTO.class));
		return pnList;
	}

}

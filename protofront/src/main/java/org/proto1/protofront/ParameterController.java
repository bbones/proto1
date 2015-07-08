/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.proto1.domain.Language;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.product.Parameter;
import org.proto1.domain.product.ParameterName;
import org.proto1.dto.ParameterDTO;
import org.proto1.dto.ParameterNameDTO;
import org.proto1.dto.UnitOfMeasurementNameDTO;
import org.proto1.services.LanguageService;
import org.proto1.services.UnitOfMeasurementService;
import org.proto1.services.parameter.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@Autowired
	UnitOfMeasurementService unitOfMeasurementService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Map<String, Object>> getParameterList(@RequestParam Long languageId) {
		return parameterService.getParameterList(languageId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody ParameterDTO getParameterList(@RequestParam Long languageId, ParameterDTO parameterDTO) {
		Parameter parameter = new Parameter();
		if (parameterDTO.getParameterId() != null)
			parameter.setId(parameterDTO.getParameterId());
		parameter.setType(Parameter.Type.valueOf(parameterDTO.getParameterType()));
		parameter.setVersion(parameterDTO.getVersion());
		Language language = languageService.get(languageId);
		parameter.getParameterNames().add(new ParameterName(parameter, parameterDTO.getParameterName(), language));
		parameter = parameterService.save(parameter);
		parameterDTO.setParameterId(parameter.getId());
		parameterDTO.setVersion(parameter.getVersion());
		
		return parameterDTO;
	}
	
	@RequestMapping(value = "/{parameterId}", method = RequestMethod.DELETE)
	public void deleteParameter(@PathVariable Long parameterId) {
		parameterService.deleteParameter(parameterId);
	}
	
	@RequestMapping(value = "{id}/names", method = RequestMethod.GET)
	public @ResponseBody List<ParameterNameDTO> getParameterNames(@PathVariable Long id) {
		List<ParameterNameDTO> pnList = new ArrayList<ParameterNameDTO>();
		for(ParameterName pn : parameterService.getNames(id))
			pnList.add(mapper.map(pn, ParameterNameDTO.class));
		return pnList;
	}

	@RequestMapping(value = "names", method = RequestMethod.POST)
	public ParameterNameDTO saveParameterName(ParameterNameDTO parameterNameDTO) {
		ParameterName parameterName = mapper.map(parameterNameDTO, ParameterName.class);
		parameterName = parameterService.saveParameterName(parameterName);
		parameterNameDTO.setParameterNameId(parameterName.getId());
		parameterNameDTO.setLanguageName(parameterName.getLanguage().getName());
		return parameterNameDTO; 
	}

	@RequestMapping(value = "names/{parameterNameId}", method = RequestMethod.DELETE)
	public void deleteParameterName(@PathVariable Long parameterNameId) {
		parameterService.deleteParameterName(parameterNameId);
	}
	
	@RequestMapping(value = "{id}/uoms/id", method = RequestMethod.GET)
	public @ResponseBody List<Long> getParameterUOMsId(@PathVariable Long id) {
		List<Long> uomIdList = new ArrayList<Long>();
		Parameter parameter = parameterService.get(id);
		for(UnitOfMeasurement uom : parameter.getAcceptedUOM()) 
			uomIdList.add(uom.getId());
		return uomIdList;
	}

	@RequestMapping(value = "{id}/uoms", method = RequestMethod.GET)
	public @ResponseBody List<Long> getParameterUOMs(@PathVariable Long id) {
		Set<UnitOfMeasurement> uomSet = parameterService.getEagerly(id).getAcceptedUOM();
		List<Long> result = new ArrayList<Long>();
		for(UnitOfMeasurement uom : uomSet) 
			result.add(uom.getId());

		return result;
	}

	@RequestMapping(value = "{parameterId}/uoms", method = RequestMethod.POST)
	public void addUOM(@PathVariable Long parameterId, @RequestParam Long uomId) {
		Parameter parameter = parameterService.get(parameterId);
		UnitOfMeasurement uom = unitOfMeasurementService.get(uomId);
		parameter.getAcceptedUOM().add(uom);
		parameterService.save(parameter);
	}

	@RequestMapping(value = "{parameterId}/uoms/{uomId}", method = RequestMethod.DELETE)
	public void removeUOM(@PathVariable Long parameterId, @PathVariable Long uomId) {
		Parameter parameter = parameterService.get(parameterId);
		UnitOfMeasurement uom = unitOfMeasurementService.get(uomId);
		parameter.getAcceptedUOM().remove(uom);
		parameterService.save(parameter);
	}


}

package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.proto1.domain.product.ParameterName;
import org.proto1.dto.ParameterNameDTO;
import org.proto1.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
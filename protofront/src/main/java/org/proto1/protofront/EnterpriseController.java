/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.EnterpriseName;
import org.proto1.dto.EnterpriseDTO;
import org.proto1.dto.EnterpriseNameDTO;
import org.proto1.dtotools.DTOMapper;
import org.proto1.services.LanguageService;
import org.proto1.services.party.EnterpriseService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enterprises")
public class EnterpriseController {
	@Autowired
	EnterpriseService enterpriseService;
	
	@Autowired
	LanguageService languageService;
	
	@Autowired
	DTOMapper mapper;
	
	@Autowired
	Mapper dozerMapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  enterpriseListByLanguage(@RequestParam Long languageId) {
		return enterpriseService.getEnterpriseList(languageId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST )
	public @ResponseBody EnterpriseDTO save(@RequestParam Long languageId, EnterpriseDTO enterpriseDTO) 
			throws BeansException, InstantiationException, 
				IllegalAccessException, SecurityException, IllegalArgumentException, 
				InvocationTargetException, NoSuchMethodException, ClassNotFoundException {
		Enterprise enterprise = mapper.decode(enterpriseDTO, Enterprise.class);
		if (enterpriseDTO.getId() == null) {
			EnterpriseName name = new EnterpriseName();
			name.setEnterprise(enterprise);
			name.setLanguage(languageService.get(languageId));
			name.setName(enterpriseDTO.getName());
			enterprise.setEnterpriseNames(new ArrayList<EnterpriseName>());
			enterprise.getEnterpriseNames().add(name);
		}
		enterprise = enterpriseService.save(enterprise);
		enterpriseDTO.setId(enterprise.getId());
		enterpriseDTO.setVersion(enterprise.getVersion());
		return enterpriseDTO;
	}

	@RequestMapping(value = "{id}/names", method = RequestMethod.GET)
	public @ResponseBody List<EnterpriseNameDTO> getEntepriseNames(@PathVariable String id) {
		List<EnterpriseNameDTO> enList = new ArrayList<EnterpriseNameDTO>();
		for(EnterpriseName en : enterpriseService.getNamesList(new Long(id)))
			enList.add(dozerMapper.map(en, EnterpriseNameDTO.class));
		return enList;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody EnterpriseDTO findByID(@PathVariable String id) {
		Enterprise enterprise = enterpriseService.get(new Long(id));
		return dozerMapper.map(enterprise, EnterpriseDTO.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		enterpriseService.delete(id);
	}


}

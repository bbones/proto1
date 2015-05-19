/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.Language;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.EnterpriseName;
import org.proto1.dto.EnterpriseDTO;
import org.proto1.dto.EnterpriseNameDTO;
import org.proto1.services.LanguageService;
import org.proto1.services.party.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {
	@Autowired
	EnterpriseService enterpriseService;
	
	@Autowired
	LanguageService languageService;
	
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  enterpriseListByLanguage(@RequestParam Long languageId) {
		return enterpriseService.getEnterpriseList(languageId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST )
	public @ResponseBody EnterpriseDTO save(EnterpriseDTO enterpriseDTO) {
		Enterprise enterprise = mapper.map(enterpriseDTO, Enterprise.class);
		if (enterpriseDTO.getNamesList() != null) {
			enterprise.setEnterpriseNames(new ArrayList<EnterpriseName>());
			for (EnterpriseNameDTO eNameDTO : enterpriseDTO.getNamesList()) {
				Language language = languageService.get(eNameDTO.getLanguageId());
				EnterpriseName enterpriseName = new EnterpriseName();
				if (eNameDTO.getEnterpriseId() != null)
					enterpriseName.setEnterprise(enterprise);
				enterpriseName.setLanguage(language);
				enterpriseName.setName(eNameDTO.getEnterpriseName());
				// enterprise.getEnterpriseNames.(new ArrayList<EnterpriseName>());
			}
		}
		enterprise = enterpriseService.save(enterprise);
		mapper.map(enterprise, enterpriseDTO);
		return enterpriseDTO;
	}

	@RequestMapping(value = "{id}/names", method = RequestMethod.GET)
	public @ResponseBody List<EnterpriseNameDTO> getEntepriseNames(@PathVariable String id) {
		List<EnterpriseNameDTO> enList = new ArrayList<EnterpriseNameDTO>();
		for(EnterpriseName en : enterpriseService.getNamesList(new Long(id)))
			enList.add(mapper.map(en, EnterpriseNameDTO.class));
		return enList;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody EnterpriseDTO findByID(@PathVariable String id) {
		Enterprise enterprise = enterpriseService.get(new Long(id));
		return mapper.map(enterprise, EnterpriseDTO.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String id) {
		enterpriseService.delete(new Long(id));
	}


}

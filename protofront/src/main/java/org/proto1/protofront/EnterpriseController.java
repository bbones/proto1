/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.EnterpriseName;
import org.proto1.dto.EnterpriseDTO;
import org.proto1.dto.EnterpriseNameDTO;
import org.proto1.services.party.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {
	@Autowired
	EnterpriseService enterpriseService;
	
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value = "listbylang/{languageId}", method = RequestMethod.POST )
	public @ResponseBody List<Map<String, Object>>  enterpriseListByLanguage(@PathVariable Long languageId) {
		return enterpriseService.getEnterpriseList(languageId);
	}
	
	@RequestMapping(value = "names/{id}", method = RequestMethod.POST)
	public @ResponseBody List<EnterpriseNameDTO> getEntepriseNames(@PathVariable String id) {
		List<EnterpriseNameDTO> enList = new ArrayList<EnterpriseNameDTO>();
		for(EnterpriseName en : enterpriseService.getNamesList(new Long(id)))
			enList.add(mapper.map(en, EnterpriseNameDTO.class));
		return enList;
	}
	
	@RequestMapping(value = "submit", method = RequestMethod.POST, produces = "application/json", consumes="application/json" )
	public @ResponseBody EnterpriseDTO submit(@RequestBody final EnterpriseDTO enterpriseDTO) {
		Enterprise enterprise = mapper.map(enterpriseDTO, Enterprise.class);
		enterprise = enterpriseService.save(enterprise);
		mapper.map(enterprise, enterpriseDTO);
		return enterpriseDTO;
	}

	@RequestMapping(value = "findByID/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody EnterpriseDTO findByID(@PathVariable String id) {
		Enterprise enterprise = enterpriseService.getEnterpriseById(new Long(id));
		return mapper.map(enterprise, EnterpriseDTO.class);
	}

	@RequestMapping(value = "deleteByID/{id}", method = RequestMethod.POST, produces = "application/json")
	public void delete(@PathVariable String id) {
		enterpriseService.delete(new Long(id));
	}


	@RequestMapping(value = "id/{id}", method = RequestMethod.GET)
	public RedirectView findByURLID(@PathVariable String id) {
		
		return new RedirectView("/protofront/index.html#enterprise:"+id);
	}
}

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
import org.proto1.dto.EnterpriseSearchDTO;
import org.proto1.dto.PagedDTO;
import org.proto1.services.LanguageService;
import org.proto1.services.party.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	Mapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  enterpriseListByLanguage(@RequestParam Long languageId) {
		return enterpriseService.getEnterpriseList(languageId);
	}
	
	@RequestMapping(value = "search", method = RequestMethod.GET)
	public @ResponseBody PagedDTO<Map<String, Object>> getList(@RequestParam Long languageId, @RequestParam String q, 
			@RequestParam int page, @RequestParam int rows) {
		Pageable p = new PageRequest(page-1, rows);
		return new PagedDTO<Map<String, Object>>(enterpriseService.getEnterpriseListCounter(languageId, "%" + q + "%"), enterpriseService.getList(languageId, "%" + q + "%", p));
	}

	@RequestMapping(value = "srchbe", method = RequestMethod.POST)
	public @ResponseBody PagedDTO<EnterpriseSearchDTO> getListBE(@RequestParam Long languageId, 
			Map<String, Object> example, 
			@RequestParam int page, @RequestParam int rows) {
		Pageable p = new PageRequest(page-1, rows);

		List<EnterpriseName> enList = enterpriseService.getList(languageId, example, p);
		List<EnterpriseSearchDTO> ensList = new ArrayList<EnterpriseSearchDTO>();
		mapper.map(enList, ensList);
		return new PagedDTO<EnterpriseSearchDTO>(
				enterpriseService.getEnterpriseListCounter(languageId, example), 
				ensList);
	}


	@RequestMapping(value = "/", method = RequestMethod.POST )
	public @ResponseBody EnterpriseDTO save(@RequestParam Long languageId, EnterpriseDTO enterpriseDTO) {
		Enterprise enterprise = mapper.map(enterpriseDTO, Enterprise.class);
		EnterpriseName en = new EnterpriseName();
		en.setEnterprise(enterprise);
		en.setLanguage(mapper.map(languageId, Language.class));
		en.setName(enterpriseDTO.getName());
		enterprise.setEnterpriseNames(new ArrayList<EnterpriseName>());
		enterprise.getEnterpriseNames().add(en);
		enterprise = enterpriseService.save(enterprise);
		enterpriseDTO.setId(enterprise.getId());
		enterpriseDTO.setVersion(enterprise.getVersion());
		return enterpriseDTO;
	}

	@RequestMapping(value = "names", method = RequestMethod.POST )
	public @ResponseBody EnterpriseNameDTO saveName(EnterpriseNameDTO enterpriseNameDTO) {
		EnterpriseName enterpriseName = mapper.map(enterpriseNameDTO, EnterpriseName.class);
		enterpriseName = enterpriseService.saveName(enterpriseName);
		enterpriseNameDTO.setEnterpriseId(enterpriseName.getId());
		enterpriseNameDTO.setVersion(enterpriseName.getVersion());
		return enterpriseNameDTO;
	}

	@RequestMapping(value = "{id}/names", method = RequestMethod.GET)
	public @ResponseBody List<EnterpriseNameDTO> getEntepriseNames(@PathVariable Long id) {
		List<EnterpriseNameDTO> enList = new ArrayList<EnterpriseNameDTO>();
		for(EnterpriseName en : enterpriseService.getNamesList(id))
			enList.add(mapper.map(en, EnterpriseNameDTO.class));
		return enList;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody EnterpriseDTO findByID(@PathVariable Long id) {
		Enterprise enterprise = enterpriseService.get(id);
		return mapper.map(enterprise, EnterpriseDTO.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		enterpriseService.delete(id);
	}

	@RequestMapping(value = "names/{id}", method = RequestMethod.DELETE)
	public void deleteName(@PathVariable Long id) {
		enterpriseService.deleteName(id);
	}


}

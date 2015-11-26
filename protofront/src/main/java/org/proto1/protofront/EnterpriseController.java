/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.Language;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.EnterpriseName;
import org.proto1.dto.EnterpriseDTO;
import org.proto1.dto.EnterpriseNameDTO;
import org.proto1.dto.PagedDTO;
import org.proto1.services.LanguageService;
import org.proto1.services.party.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	public @ResponseBody PagedDTO<EnterpriseDTO> getListBE(@RequestParam Integer page, @RequestParam Integer rows, @RequestParam Long languageId, 
				MultiValueMap<String, Object> example) {
		
		Pageable p = new PageRequest(page-1, rows);
		return null;
		// new PagedDTO<EnterpriseDTO>(enterpriseService.getEnterpriseListCounter(languageId, example), 
		//		enterpriseService.getList(languageId, example, p));
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

	@RequestMapping(value = "esindex", method = RequestMethod.GET)
	public String indexEnterprises() throws IOException {
		return enterpriseService.esindex();
	}
}

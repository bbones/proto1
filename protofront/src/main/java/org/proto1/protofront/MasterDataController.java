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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.Currency;
import org.proto1.dto.LanguageDTO;
import org.proto1.dto.PagedDTO;
import org.proto1.services.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@RequestMapping(value = "currencies", method = RequestMethod.GET)
	public @ResponseBody List<Currency> getCurrencyList() {
		return masterDataService.getCurrencyList();
	}


	@RequestMapping(value = "parties", method = RequestMethod.GET)
	public @ResponseBody PagedDTO<Map<String, Object>> getPartyList(@RequestParam Long languageId, @RequestParam String q, 
			@RequestParam int page, @RequestParam int rows) {
		Pageable p = new PageRequest(page-1, rows);
		return new PagedDTO<Map<String, Object>>(masterDataService.getParyListCounter(languageId, "%" + q + "%"), masterDataService.getParyList(languageId, "%" + q + "%", p));
	}

}

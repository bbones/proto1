/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.Language;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.UnitOfMeasurementName;
import org.proto1.dto.UnitOfMeasurementDTO;
import org.proto1.dto.UnitOfMeasurementNameDTO;
import org.proto1.services.LanguageService;
import org.proto1.services.UnitOfMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/uoms")
public class UOMController {
	@Autowired
	UnitOfMeasurementService unitOfMeasurementService;
	
	@Autowired
	LanguageService languageService;
	
	@Autowired
	Mapper mapper;
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getUOMList(@RequestParam Long languageId) {
		return unitOfMeasurementService.getUOMList(languageId);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody UnitOfMeasurementDTO save(@RequestParam Long languageId, 
			UnitOfMeasurementDTO uomDTO) {
		UnitOfMeasurement uom = new UnitOfMeasurement();
		if (uomDTO.getId()!=null)
			uom.setId(uomDTO.getId());
		Language language = languageService.get(languageId);
		
		UnitOfMeasurementName uomName =  new UnitOfMeasurementName();
		uomName.setShortName(uomDTO.getShortName());
		uomName.setFullName(uomDTO.getFullName());
		uomName.setLanguage(language);
		uomName.setUnitOfMeasurement(uom);
		uom.getUnitOfMeasurementNames().add(uomName);
		uom = unitOfMeasurementService.save(uom);
		uomDTO.setId(uom.getId());
		return uomDTO;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		unitOfMeasurementService.delete(id);
	}

	@RequestMapping(value = "{uomId}/names", method = RequestMethod.GET)
	public @ResponseBody List<UnitOfMeasurementNameDTO> getNamesList(@PathVariable Long uomId) {
		List<UnitOfMeasurementNameDTO> uomNamesDTO = new ArrayList<UnitOfMeasurementNameDTO>();
		
		List<UnitOfMeasurementName> uomNamesList = unitOfMeasurementService.getUOMNamesList(uomId);
		for(UnitOfMeasurementName uomn : uomNamesList) 
			uomNamesDTO.add(mapper.map(uomn, UnitOfMeasurementNameDTO.class)); 
		return uomNamesDTO;
	}

	@RequestMapping(value = "names", method = RequestMethod.POST)
	public @ResponseBody UnitOfMeasurementNameDTO saveName(UnitOfMeasurementNameDTO uomNameDTO) {
		UnitOfMeasurementName uomName= mapper.map(uomNameDTO, UnitOfMeasurementName.class);
		uomName = unitOfMeasurementService.saveName(uomName);
		uomNameDTO.setNameId(uomName.getId());
		return uomNameDTO;
	}

	@RequestMapping(value = "names/{nameId}", method = RequestMethod.DELETE)
	public void deleteName(@PathVariable Long nameId) {
		unitOfMeasurementService.deleteName(nameId);
	}

}

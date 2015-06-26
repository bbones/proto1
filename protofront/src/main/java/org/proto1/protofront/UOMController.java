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
		UnitOfMeasurement uom = unitOfMeasurementService.get(uomId);
		List<UnitOfMeasurementNameDTO> uomNameDTOs = new ArrayList<UnitOfMeasurementNameDTO>();
		for (UnitOfMeasurementName uomName : uom.getUnitOfMeasurementNames()) {
			UnitOfMeasurementNameDTO uomNameDTO = mapper.map(uomName, UnitOfMeasurementNameDTO.class);
			uomNameDTOs.add(uomNameDTO);
		}
		return uomNameDTOs;
	}

	@RequestMapping(value = "{uomId}/names", method = RequestMethod.POST)
	public @ResponseBody UnitOfMeasurementNameDTO saveName(@PathVariable Long uomId, UnitOfMeasurementNameDTO uomNameDTO) {
		UnitOfMeasurementName uomName =  new UnitOfMeasurementName();
		UnitOfMeasurement uom = unitOfMeasurementService.get(uomId);
		uomName.setUnitOfMeasurement(uom);

		uomName.setShortName(uomNameDTO.getShortName());
		uomName.setFullName(uomNameDTO.getFullName());
		Language language = languageService.get(uomNameDTO.getLanguageId());
		uomName.setLanguage(language);

		uomName = unitOfMeasurementService.saveName(uomName);
		uomNameDTO.setNameId(uomName.getId());
		return uomNameDTO;
	}

	@RequestMapping(value = "{id}/names/{nameId}", method = RequestMethod.DELETE)
	public void deleteName(@PathVariable Long id, @PathVariable Long nameId) {
		unitOfMeasurementService.deleteName(nameId);
	}

}

package org.proto1.protofront;

import java.util.List;
import java.util.Map;

import org.proto1.domain.Language;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.UnitOfMeasurementName;
import org.proto1.dto.UnitOfMeasurementDTO;
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
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getUOMList(@RequestParam Long languageId) {
		return unitOfMeasurementService.getUOMList(languageId);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody UnitOfMeasurementDTO save(@RequestParam Long languageId, 
			UnitOfMeasurementDTO uomDTO) {
		UnitOfMeasurement uom = new UnitOfMeasurement();
		if (uomDTO.getUomId()!=null)
			uom.setId(uomDTO.getUomId());
		Language language = languageService.get(languageId);
		
		UnitOfMeasurementName uomName =  new UnitOfMeasurementName();
		uomName.setShortName(uomDTO.getUomShortName());
		uomName.setFullName(uomDTO.getUomFullName());
		uomName.setLanguage(language);
		uomName.setUnitOfMeasurement(uom);
		uom.getUnitOfMeasurementNames().add(uomName);
		uom = unitOfMeasurementService.save(uom);
		uomDTO.setUomId(uom.getId());
		return uomDTO;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		unitOfMeasurementService.delete(id);
	}


}

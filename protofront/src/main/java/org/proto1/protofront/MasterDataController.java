package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.proto1.dto.LanguageDTO;
import org.proto1.services.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class MasterDataController {
	@Autowired
	Mapper mapper;
	
	@Autowired
	MasterDataService masterDataService;
	
	@RequestMapping(value = "languagelist", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<LanguageDTO> getLanguageList() {
		List<LanguageDTO> langlist = new ArrayList<LanguageDTO>();
		// mapper(
		return null;
	}

}

package org.proto1.protofront;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.Language;
import org.proto1.domain.party.OrganizationUnit;
import org.proto1.domain.party.OrganizationUnitName;
import org.proto1.dto.OrganizationUnitDTO;
import org.proto1.dto.OrganizationUnitNameDTO;
import org.proto1.services.party.OrganizationUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orgunits")
public class OrganizationUnitController extends BaseController {
	@Autowired
	OrganizationUnitService organizationUnitService;
	
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET )
	public @ResponseBody List<Map<String, Object>>  getList(@RequestParam Long languageId) {
		return organizationUnitService.getList(languageId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST )
	public @ResponseBody OrganizationUnitDTO save(@RequestParam Long languageId, OrganizationUnitDTO organizationUnitDTO) {
		OrganizationUnit ou = mapper.map(organizationUnitDTO, OrganizationUnit.class);
		OrganizationUnitName oun = new OrganizationUnitName();
		oun.setLanguage(mapper.map(languageId,  Language.class));
		oun.setOrganizationUnit(ou);
		oun.setUnitName(organizationUnitDTO.getName());
		ou.getNames().add(oun);
		organizationUnitService.save(ou);
		return organizationUnitDTO;
		
	}

	@RequestMapping(value = "{id}/names", method = RequestMethod.GET)
	public @ResponseBody List<OrganizationUnitNameDTO> getEntepriseNames(@PathVariable Long id) {
		List<OrganizationUnitNameDTO> ounList = new ArrayList<OrganizationUnitNameDTO>();
		for(OrganizationUnitName oun : organizationUnitService.getNamesList(id))
			ounList.add(mapper.map(oun, OrganizationUnitNameDTO.class));
		return ounList;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		organizationUnitService.delete(id);
	}


}
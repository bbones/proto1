/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.Contract;
import org.proto1.domain.ContractSide;
import org.proto1.dto.ContractDTO;
import org.proto1.dto.ContractSideDTO;
import org.proto1.dtotools.DTODecode;
import org.proto1.dtotools.DTOMapper;
import org.proto1.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contracts")
public class ContractController {
	@Autowired
	ContractService contractService;
	
	@Autowired
	DTOMapper dtoMapper;
	
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getList() {
		return contractService.getContracts();
	}


	@RequestMapping(value = "/", method = RequestMethod.POST )
	public @ResponseBody ContractDTO submit(ContractDTO contractDTO) {
		Contract contract = mapper.map(contractDTO, Contract.class);
		contract = contractService.save(contract);
		mapper.map(contract, contractDTO);
		return contractDTO;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ContractDTO get(@PathVariable Long id) {
		Contract contract = contractService.getContractById(new Long(id));
		return mapper.map(contract, ContractDTO.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		contractService.delete(new Long(id));
	}

	@RequestMapping(value = "/{contractId}/supplements", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getSupplements(@PathVariable Long contractId) {
		return contractService.getSupplements(contractId);
	}

	@RequestMapping(value = "/sides", method = RequestMethod.POST )
	public @ResponseBody ContractSideDTO submit(@RequestParam Long languageId, ContractSideDTO contractSideDTO) {
		ContractSide contractSide = dtoMapper.decode(contractSideDTO, ContractSide.class);
		contractSide = contractService.saveSide(contractSide);
		return contractSideDTO;
	}

	@RequestMapping(value = "/{contractId}/sides", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getSides(@PathVariable Long contractId, @RequestParam Long languageId) {
		return contractService.getSides(contractId, languageId);
	}

	@RequestMapping(value = "roles", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getRoleList(@RequestParam Long languageId) {
		return contractService.getRoles(languageId);
	}
	
	@InitBinder
	public void binder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
		    public void setAsText(String value) {
		        try {
		            setValue(new SimpleDateFormat("dd.MM.yyyy").parse(value));
		        } catch(ParseException e) {
		            setValue(null);
		        }
		    }

		    public String getAsText() {
		        return new SimpleDateFormat("dd.MM.yyyy").format((Date) getValue());
		    }        

		});	    // as shown above
	}

}

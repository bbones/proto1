/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.protofront;

import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.proto1.domain.Contract;
import org.proto1.dto.ContractDTO;
import org.proto1.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
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
	Mapper mapper;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getList() {
		return contractService.getContracts();
	}


	@RequestMapping(value = "/", method = RequestMethod.POST )
	public @ResponseBody ContractDTO submit(@RequestBody final ContractDTO contractDTO) {
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

	@RequestMapping(value = "/{contractId}/sides", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getSides(@PathVariable Long contractId, @RequestParam Long languageId) {
		return contractService.getSides(contractId, languageId);
	}

	@RequestMapping(value = "roles", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getRoleList(@RequestParam Long languageId) {
		return contractService.getRoles(languageId);
	}

	
	
}

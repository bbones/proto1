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
import org.proto1.domain.Contract;
import org.proto1.domain.ContractSide;
import org.proto1.domain.ContractSupplement;
import org.proto1.dto.ContractDTO;
import org.proto1.dto.ContractSideDTO;
import org.proto1.dto.ContractSupplementDTO;
import org.proto1.services.ContractService;
import org.proto1.services.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contracts")
public class ContractController extends BaseController {
	@Autowired
	ContractService contractService;

	@Autowired
	LanguageService languageService;

	@Autowired
	Mapper mapper;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getList() {
		return contractService.getContracts();
	}

	@RequestMapping(value = "/findAll", method = RequestMethod.POST)
	public @ResponseBody List<ContractDTO> findAll(Contract criteria) {
		List<Contract> contractList = contractService.findAll(criteria);
		List<ContractDTO> contractDTOList = new ArrayList<ContractDTO>();
		for (Contract contract : contractList) {
			contractDTOList.add((ContractDTO) mapper.map(contract,
					ContractDTO.class));
		}
		return contractDTOList;
	}

	// EasyUI Form submit

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public @ResponseBody ContractDTO submit(ContractDTO contractDTO) {
		Contract contract = mapper.map(contractDTO, Contract.class);
		contract = contractService.save(contract);
		mapper.map(contract, contractDTO);
		return contractDTO;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody ContractDTO get(@PathVariable Long id) {
		Contract contract = contractService.getContract(new Long(id));
		return mapper.map(contract, ContractDTO.class);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		contractService.delete(new Long(id));
	}

	@RequestMapping(value = "/{contractId}/supplements", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getSupplements(
			@PathVariable Long contractId) {
		return contractService.getSupplements(contractId);
	}

	@RequestMapping(value = "/sides", method = RequestMethod.POST)
	public @ResponseBody ContractSideDTO submit(@RequestParam Long languageId,
			ContractSideDTO contractSideDTO) {
		ContractSide contractSide = mapper.map(contractSideDTO,
				ContractSide.class);
		contractSide = contractService.saveSide(contractSide);
		contractSideDTO.setCsId(contractSide.getId());
		contractSideDTO.setPartyName(contractSide.getParty().getName(
				languageService.get(languageId)));
		return contractSideDTO;
	}

	@RequestMapping(value = "/sides/{id}", method = RequestMethod.DELETE)
	public void deleteSide(@PathVariable Long id) {
		contractService.deleteSide(id);
	}

	@RequestMapping(value = "/{contractId}/sides", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getSides(
			@PathVariable Long contractId, @RequestParam Long languageId) {
		return contractService.getSides(contractId, languageId);
	}

	@RequestMapping(value = "roles", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getRoleList(
			@RequestParam Long languageId) {
		return contractService.getRoles(languageId);
	}

	@RequestMapping(value = "/supplements/{supplementId}", method = RequestMethod.GET)
	public @ResponseBody ContractSupplementDTO getSupplement(
			@PathVariable Long supplementId) {
		ContractSupplement contractSupplement = contractService
				.getSupplement(supplementId);
		ContractSupplementDTO result = mapper.map(contractSupplement,
				ContractSupplementDTO.class);
		return result;
	}

	@RequestMapping(value = "/supplements", method = RequestMethod.POST)
	public @ResponseBody ContractSupplementDTO submitSupplement(
			ContractSupplementDTO contractSupplementDTO) {
		ContractSupplement contractSupplement = mapper.map(
				contractSupplementDTO, ContractSupplement.class);
		contractSupplement = contractService.saveSupplement(contractSupplement);
		contractSupplementDTO.setId(contractSupplement.getId());
		return contractSupplementDTO;
	}

	@RequestMapping(value = "/supplements/{id}", method = RequestMethod.DELETE)
	public void deleteSupplement(@PathVariable Long supplementId) {
		contractService.deleteSupplement(supplementId);
	}

}

/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.proto1.domain.Contract;
import org.proto1.domain.ContractSide;
import org.proto1.domain.ContractSupplement;
import org.proto1.domain.SideRole;
import org.proto1.repository.ContractRepository;

public interface ContractService {

	List<Map<String, Object>> getContracts();
	
	void setContractRepository(ContractRepository crep);

	Contract getContract(Long id);

	Contract save(Contract contract);

	void delete(Long id);

	/**
	 * @param contractId
	 * @return
	 */
	List<Map<String, Object>> getSupplements(Long contractId);

	/**
	 * @param contractId
	 * @return
	 */
	List<Map<String, Object>> getSides(Long contractId,Long languageId);

	/**
	 * @param languageId
	 * @return
	 */
	List<Map<String, Object>> getRoles(Long languageId);
	
	SideRole getRole(Long id);
	
	ContractSide saveSide(ContractSide contractSide);

	void deleteSide(Long id);

	ContractSupplement getSupplement(Long supplementId);

}

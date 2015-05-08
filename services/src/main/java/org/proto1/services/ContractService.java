/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.proto1.domain.Contract;
import org.proto1.repository.ContractRepository;

public interface ContractService {

	List<Map<String, Object>> getContracts();
	
	void setContractRepository(ContractRepository crep);

	Contract getContractById(Long id);

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

}

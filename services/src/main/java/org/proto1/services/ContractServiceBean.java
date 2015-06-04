/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.proto1.domain.Contract;
import org.proto1.domain.ContractSide;
import org.proto1.domain.ContractSupplement;
import org.proto1.domain.SideRole;
import org.proto1.repository.ContractRepository;
import org.proto1.repository.ContractSideRepository;
import org.proto1.repository.ContractSupplementRepository;
import org.proto1.repository.SideRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceBean implements ContractService {
	@Autowired
	ContractRepository contractRepository;
	
	@Autowired
	ContractSupplementRepository contractSupplementRepository;

	@Autowired
	ContractSideRepository contractSideRepository;
	
	@Autowired
	SideRoleRepository sideRoleRepository;
	
	public void setContractRepository(ContractRepository contractRepository) {
		this.contractRepository = contractRepository;
		
	}

	public Contract getContract(Long id) {
		return contractRepository.findOne(id);
	}

	public Contract save(Contract contract) {
		return contractRepository.save(contract);
	}

	public void delete(Long id) {
		contractRepository.delete(id);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.ContractService#getContracts()
	 */
	@Transactional
	public List<Map<String, Object>> getContracts() {
		return contractRepository.list();
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.ContractService#getSupplements(java.lang.Long)
	 */
	public List<Map<String, Object>> getSupplements(Long contractId) {
		return contractSupplementRepository.list(contractId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.ContractService#getSides(java.lang.Long)
	 */
	public List<Map<String, Object>> getSides(Long contractId, Long languageId) {
		return contractSideRepository.list(contractId, languageId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.ContractService#getRoles(java.lang.Long)
	 */
	public List<Map<String, Object>> getRoles(Long languageId) {
		return sideRoleRepository.getList(languageId);
	}

	public ContractSide saveSide(ContractSide contractSide) {
		return contractSideRepository.save(contractSide);
	}

	public SideRole getRole(Long id) {
		return sideRoleRepository.findOne(id);
	}

	public void deleteSide(Long id) {
		contractSideRepository.delete(id);
		
	}

	public ContractSupplement getSupplement(Long supplementId) {
		return contractSupplementRepository.findOne(supplementId);
	}

	public ContractSupplement saveSupplement(ContractSupplement cs) {
		return contractSupplementRepository.save(cs);
	}

}

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
package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.proto1.domain.Contract;
import org.proto1.domain.ContractSide;
import org.proto1.domain.ContractSupplement;
import org.proto1.domain.SideRole;
import org.proto1.repository.ContractRepository;
import org.proto1.repository.ContractSideRepository;
import org.proto1.repository.ContractSupplementRepository;
import org.proto1.repository.SideRoleRepository;
import org.proto1.specifications.PlainSpecification;
import org.proto1.specifications.SearchCriteria;
import org.proto1.workflow.ApproveDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.security.access.annotation.Secured;
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
	@Autowired 
	ApproveDocumentService approveDocumentServiceBean;
	protected final Log logger = LogFactory.getLog(getClass());
	public void setContractRepository(ContractRepository contractRepository) {
		this.contractRepository = contractRepository;

	}

	public Contract getContract(Long id) {
		return contractRepository.findOne(id);
	}

	public Contract save(Contract contract) {
		Contract result = contractRepository.save(contract);
		// TODO: Пример запуска workFlow. Продумать, где передавать тип approve
		approveDocumentServiceBean.startApproveByName(result.getId(), "Утверждение договора");
		return result;
	}

	public void delete(Long id) {
		contractRepository.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.proto1.services.ContractService#getContracts()
	 */
	public List<Map<String, Object>> getContracts() {
		return contractRepository.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.proto1.services.ContractService#getSupplements(java.lang.Long)
	 */
	@Secured("ROLE_SUPERUSER")
	public List<Map<String, Object>> getSupplements(Long contractId) {
		return contractSupplementRepository.list(contractId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.proto1.services.ContractService#getSides(java.lang.Long)
	 */
	public List<Map<String, Object>> getSides(Long contractId, Long languageId) {
		return contractSideRepository.list(contractId, languageId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.proto1.services.ContractService#getRoles(java.lang.Long)
	 */
	public List<Map<String, Object>> getRoles(Long languageId) {
		return sideRoleRepository.getList(languageId);
	}

	public ContractSide saveSide(ContractSide contractSide) {
		Contract result = contractSide.getContract();
		// TODO: Пример запуска workFlow. Продумать, где передавать тип approve
		approveDocumentServiceBean.startApproveByName(result.getId(), "Утверждение договора");
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

	public void deleteSupplement(Long supplementId) {
		contractSupplementRepository.delete(supplementId);
	}

	public List<Contract> findAll(Contract criteria) {
	PlainSpecification<Contract> spec1 = 	
		      new PlainSpecification<Contract>(new SearchCriteria("id", "=", criteria.getId()));
	PlainSpecification<Contract> spec2 = 	
		      new PlainSpecification<Contract>(new SearchCriteria("documentNo", "like", criteria.getDocumentNo()));
	PlainSpecification<Contract> spec3 = 	
		      new PlainSpecification<Contract>(new SearchCriteria("issueDate", "=", criteria.getIssueDate()));
	return contractRepository.findAll(Specifications.where(spec1).and(spec2).and(spec3));
	}
}

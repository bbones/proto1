package org.proto1.services;

import org.proto1.domain.Contract;
import org.proto1.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceBean implements ContractService {
	@Autowired
	ContractRepository contractRepository;

	public void setContractRepository(ContractRepository contractRepository) {
		this.contractRepository = contractRepository;
		
	}

	public Contract getContractById(Long id) {
		return contractRepository.findOne(id);
	}

	public Contract save(Contract contract) {
		return contractRepository.save(contract);
	}

	public void delete(Long id) {
		contractRepository.delete(id);
	}

}

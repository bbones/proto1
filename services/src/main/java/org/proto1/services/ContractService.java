package org.proto1.services;

import org.proto1.domain.Contract;
import org.proto1.repository.ContractRepository;

public interface ContractService {

	void setContractRepository(ContractRepository crep);

	Contract getContractById(Long id);

	Contract save(Contract contract);

	void delete(Long id);

}

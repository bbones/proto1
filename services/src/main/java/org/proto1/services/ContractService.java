package org.proto1.services;

import org.proto1.domain.Contract;
import org.proto1.repository.ContractRepository;

public interface ContractService {

	void setContractRepository(ContractRepository crep);

	Object getContractById(long l);

	Object save(Contract contract);

}

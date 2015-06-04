package org.proto1.mapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.DozerConverter;
import org.proto1.domain.Contract;
import org.proto1.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LongContractConverter extends DozerConverter<Long, Contract> {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	ContractService contractService;
	
	public LongContractConverter() {
		super(Long.class, Contract.class);
	}

	@Override
	public Contract convertTo(Long source, Contract destination) {
		return contractService.getContract(source);
	}

	@Override
	public Long convertFrom(Contract source, Long destination) {
		return source.getId();
	}

}

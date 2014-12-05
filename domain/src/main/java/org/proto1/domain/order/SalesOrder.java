package org.proto1.domain.order;

import org.proto1.domain.ContractSupplement;

public class SalesOrder extends Order {
	
	private ContractSupplement contractSupplement;

	public ContractSupplement getContractSupplement() {
		return contractSupplement;
	}

	public void setContractSupplement(ContractSupplement contractSupplement) {
		this.contractSupplement = contractSupplement;
	}

}

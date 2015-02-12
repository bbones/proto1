package org.proto1.domain.order;

import javax.persistence.Entity;

import org.proto1.domain.ContractSupplement;

@Entity
public class SalesOrder extends Order {
	
	private ContractSupplement contractSupplement;

	public ContractSupplement getContractSupplement() {
		return contractSupplement;
	}

	public void setContractSupplement(ContractSupplement contractSupplement) {
		this.contractSupplement = contractSupplement;
	}

}

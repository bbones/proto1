package org.proto1.domain.order;

import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.proto1.domain.ContractSupplement;

@Entity
public class SalesOrder extends BaseOrder {
	
	@ManyToOne
	@JoinColumn(name="CONTRACT_SUPPLEMENT_ID")
	private ContractSupplement contractSupplement;

	public ContractSupplement getContractSupplement() {
		return contractSupplement;
	}

	public void setContractSupplement(ContractSupplement contractSupplement) {
		this.contractSupplement = contractSupplement;
	}

}

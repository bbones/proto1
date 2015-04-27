/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.order;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.proto1.domain.ContractSupplement;

@Entity
public class PurchaseOrder extends BaseOrder {
	@ManyToOne
	@JoinColumn(name="CONTRACT_SUPPLEMENT_ID")
	@NotNull
	private ContractSupplement contractSupplement;

	public ContractSupplement getContractSupplement() {
		return contractSupplement;
	}

	public void setContractSupplement(ContractSupplement contractSupplement) {
		this.contractSupplement = contractSupplement;
	}

}

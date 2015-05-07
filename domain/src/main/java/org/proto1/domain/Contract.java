/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="CONTRACT_ID")
public class Contract extends Document {
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST, mappedBy="contract")
	private List<ContractSide> contractSides;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST, mappedBy="contract")
	private List<ContractSupplement> contractSupplements;

	public List<ContractSide> getContractSides() {
		return contractSides;
	}

	public void setContractSides(List<ContractSide> contractSides) {
		this.contractSides = contractSides;
	}

	public List<ContractSupplement> getContractSupplements() {
		return contractSupplements;
	}

	public void setContractSupplements(List<ContractSupplement> contractSupplements) {
		this.contractSupplements = contractSupplements;
	}
	
}

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
package org.proto1.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="CONTRACT_ID")
public class Contract extends Document {
	@OneToMany(fetch=FetchType.EAGER, mappedBy="contract")
	private List<ContractSide> contractSides;

	@OneToMany(fetch=FetchType.EAGER, mappedBy="contract")
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

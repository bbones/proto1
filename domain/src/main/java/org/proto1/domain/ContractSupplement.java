/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="CONTRACT_SUPPLEMENT_ID")
public class ContractSupplement extends Document {
	@ManyToOne
	@JoinColumn(name="CONTRACT_ID")
	private Contract contract;
	
	@ManyToOne
	@JoinColumn(name="CURRENCY_CODE")
	private Currency currency;

	public Contract getContract() {
		return contract;
	}


	public void setContract(Contract contract) {
		this.contract = contract;
	}
	
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}

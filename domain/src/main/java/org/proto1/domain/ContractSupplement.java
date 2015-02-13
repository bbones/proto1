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
	
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
}

package org.proto1.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ContractSide extends AbstractEntity {
	
	
	@ManyToOne
	@JoinColumn(name="contract_id")
	private Contract contract;
	
	@ManyToOne
	@JoinColumn(name="side_role_id")
	private SideRole sideRole;
	
	@ManyToOne
	@JoinColumn(name="party_id")
	private Party party;
	
	public SideRole getSideRole() {
		return sideRole;
	}
	
	public void setSideRole(SideRole sideRole) {
		this.sideRole = sideRole;
	}
	
	public Party getParty() {
		return party;
	}
	
	public void setParty(Party party) {
		this.party = party;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}
}

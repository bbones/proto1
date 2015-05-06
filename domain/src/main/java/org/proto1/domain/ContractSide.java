/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.proto1.domain.party.Party;

@Entity
public class ContractSide extends AbstractEntity {
	
	
	@ManyToOne
	@JoinColumn(name="contract_id")
	@NotNull
	private Contract contract;
	
	@ManyToOne
	@JoinColumn(name="side_role_id")
	@NotNull
	private SideRole sideRole;
	
	@ManyToOne
	@JoinColumn(name="party_id")
	@NotNull
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

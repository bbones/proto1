/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
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

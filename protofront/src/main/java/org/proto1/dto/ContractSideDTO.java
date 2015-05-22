package org.proto1.dto;

import org.proto1.dtotools.DTODecode;

public class ContractSideDTO extends DTO {

	private static final long serialVersionUID = -8649332493146511083L;

	private Long csId;
	private Long contractId;
	private Long roleId;
	private Long partyId;
	private String partyName;

	@DTODecode(destination = "setId")
	public Long getCsId() {
		return csId;
	}

	public void setCsId(Long csId) {
		this.csId = csId;
	}

	@DTODecode(destination="setContract", service="org.proto1.services.ContractService", method="getContract")
	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	@DTODecode(destination="setSideRole", service="org.proto1.services.ContractService", method="getRole")
	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@DTODecode(destination="setParty", service="org.proto1.services.PartyService", method="getParty")
	public Long getPartyId() {
		return partyId;
	}

	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

}

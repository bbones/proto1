package org.proto1.dto;

public class ContractSideDTO extends DTO {

	private static final long serialVersionUID = -8649332493146511083L;
	
	private Long contractSideId;
	private Long contractId;
	private Long sideRoleId;
	private Long partyId;
	
	public Long getContractSideId() {
		return contractSideId;
	}
	public void setContractSideId(Long contractSideId) {
		this.contractSideId = contractSideId;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public Long getSideRoleId() {
		return sideRoleId;
	}
	public void setSideRoleId(Long sideRoleId) {
		this.sideRoleId = sideRoleId;
	}
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}


}

package org.proto1.dto;

public class ContractSupplementDTO extends DocumentDTO {

	private static final long serialVersionUID = 8245373285664597984L;

	private Long contractId;
	private Long currencyId;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

}

package org.proto1.dto;

import org.proto1.dtotools.DTODecode;

public class ContractSupplementDTO extends DocumentDTO {

	private static final long serialVersionUID = 8245373285664597984L;

	private Long contractId;
	private Long currencyId;

	@DTODecode(destination="setContract",service="ContractService", method="get")
	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	@DTODecode(destination="setCurrency", service="MasterDataService", method="getCurrency")
	public Long getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Long currencyId) {
		this.currencyId = currencyId;
	}

}

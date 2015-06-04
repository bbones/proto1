package org.proto1.dto;

import org.proto1.dtotools.DTODecode;

public class ContractSupplementDTO extends DocumentDTO {

	private static final long serialVersionUID = 8245373285664597984L;

	private Long contractId;
	private Integer currencyId;

	public Long getContractId() {
		return contractId;
	}

	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}

	public Integer getCurrencyId() {
		return currencyId;
	}

	public void setCurrencyId(Integer currencyId) {
		this.currencyId = currencyId;
	}

}

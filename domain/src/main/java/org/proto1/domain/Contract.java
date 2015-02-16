package org.proto1.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="CONTRACT_ID")
public class Contract extends Document {
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="contract")
	private Set<ContractSide> contractSides;

	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="contract")
	private List<ContractSupplement> contractSupplements;

	public Set<ContractSide> getContractSides() {
		return contractSides;
	}

	public void setContractSides(Set<ContractSide> contractSides) {
		this.contractSides = contractSides;
	}

	public List<ContractSupplement> getContractSupplements() {
		return contractSupplements;
	}

	public void setContractSupplements(List<ContractSupplement> contractSupplements) {
		this.contractSupplements = contractSupplements;
	}
	
}

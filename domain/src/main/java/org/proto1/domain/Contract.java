package org.proto1.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Contract extends Document {
	private Date issueDate;
	
	@OneToMany
	@JoinColumn(name="contract_id",nullable=true)
	private Set<ContractSide> contractSides;

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Set<ContractSide> getContractSides() {
		return contractSides;
	}

	public void setContractSides(Set<ContractSide> contractSides) {
		this.contractSides = contractSides;
	}
}

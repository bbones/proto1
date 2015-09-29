package org.proto1.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="APPROVE_TYPE")
public class ApproveType extends AbstractEntity {

	@NotNull
	private String name;
	@NotNull
	private String description;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy="approveType")
	private List<Approve> approves;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Approve> getApproves() {
		return approves;
	}

	public void setApproves(List<Approve> approves) {
		this.approves = approves;
	}

}

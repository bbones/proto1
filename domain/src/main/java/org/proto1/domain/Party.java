package org.proto1.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(
		strategy = InheritanceType.JOINED
) 
@NamedQueries({
	@NamedQuery(name="partyList", query="select new Map(p.id as id, coalesce(p.name, p.lastName + ' ' + p.firstName) as name) from Party p")
})
public abstract class  Party implements Identified {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PARTY_ID")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
}

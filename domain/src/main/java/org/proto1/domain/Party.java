package org.proto1.domain;

import javax.persistence.Entity;
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
public abstract class  Party extends AbstractEntity {
	@NotNull
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}

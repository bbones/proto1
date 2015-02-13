package org.proto1.domain.party;

import javax.validation.constraints.NotNull;

import org.proto1.domain.AbstractEntity;

public class EnterpriseName extends AbstractEntity {
	@NotNull
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

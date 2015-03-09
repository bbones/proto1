package org.proto1.domain.inventory;

import org.proto1.domain.AbstractEntity;

public class Location extends AbstractEntity {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

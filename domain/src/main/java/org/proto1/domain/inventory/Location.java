package org.proto1.domain.inventory;

import javax.persistence.Entity;

import org.proto1.domain.AbstractEntity;

@Entity
public class Location extends AbstractEntity {
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

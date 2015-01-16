package org.proto1.domain.product;

import javax.persistence.Entity;

import org.proto1.domain.AbstractEntity;

@Entity
public class Parameter extends AbstractEntity {
	public enum Type {
		STRING, NUMBER, DATE
	}

	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

}

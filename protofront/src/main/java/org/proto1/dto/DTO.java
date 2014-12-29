package org.proto1.dto;

import java.io.Serializable;

public abstract class DTO implements Serializable{
	
	protected Integer version;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
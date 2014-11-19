package org.proto1.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Period {
	private Long id;

	@Id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

package org.proto1.domain.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.proto1.domain.Identified;

@Entity
public class Product implements Identified {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PARTY_ID")
	private Long id;
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}

/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.domain.accounting;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.proto1.domain.Identified;

@Entity
public class Account implements Identified {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	@OneToMany
	private Set<AnaliticRole> analitics;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AnaliticRole> getAnalitics() {
		return analitics;
	}

	public void setAnalitics(Set<AnaliticRole> analitics) {
		this.analitics = analitics;
	}
}

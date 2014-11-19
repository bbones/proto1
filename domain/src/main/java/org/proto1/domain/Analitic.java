package org.proto1.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.proto1.domain.accounting.AnaliticRole;

@Entity
public class Analitic {
	@Id
	private Long id;
	
	private Class<Identified> clazz;
	
	@ManyToOne
	private AnaliticRole analiticRole;
	
	public Analitic() {
		
	}
	
	public Analitic(Identified obj) {
		clazz = (Class<Identified>) obj.getClass();
	}
	
	public Class<Identified> getAnaliticClass() {
		return clazz;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AnaliticRole getAnaliticRole() {
		return analiticRole;
	}

	public void setAnaliticRole(AnaliticRole analiticRole) {
		this.analiticRole = analiticRole;
	}
	
}

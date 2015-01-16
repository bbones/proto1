package org.proto1.domain.product;

import javax.persistence.Entity;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.Language;

@Entity
public class ParameterName extends AbstractEntity {

	private Parameter parameter;
	private Language language;
	private String name;

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

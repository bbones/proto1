package org.proto1.domain.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.proto1.domain.AbstractEntity;

@Entity
public class Parameter extends AbstractEntity {
	public enum Type {
		STRING, NUMBER, DATE
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL, mappedBy="parameter")
	private List<ParameterName> parameterNames = new ArrayList<ParameterName>();

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<ParameterName> getParameterNames() {
		return parameterNames;
	}

	public void setParameterNames(List<ParameterName> parameterNames) {
		this.parameterNames = parameterNames;
	}
	

}

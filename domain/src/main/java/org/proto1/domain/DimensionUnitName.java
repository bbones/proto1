package org.proto1.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="DIM_UNIT_NAME", uniqueConstraints=@UniqueConstraint(columnNames = {"DIM_UNIT_ID", "LANGUAGE_ID"})) 
public class DimensionUnitName extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name="DIM_UNIT_ID")
	private DimensionUnit dimensionUnit;
	
	@ManyToOne
	@JoinColumn(name="language_id")
	private Language language;

	private String shortName;
	private String fullName;

	
	public DimensionUnit getDimensionUnit() {
		return dimensionUnit;
	}

	public void setDimensionUnit(DimensionUnit dimensionUnit) {
		this.dimensionUnit = dimensionUnit;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	
}

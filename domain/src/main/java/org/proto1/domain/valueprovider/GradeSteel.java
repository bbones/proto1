package org.proto1.domain.valueprovider;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.proto1.domain.AbstractEntity;

@Entity
@Table(name="GRADE_OF_STEEL", schema="VALUEPROVIDER")
public class GradeSteel extends AbstractEntity {
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="STEEL_STANDARD_ID")
	private GradeSteelStandard gradeSteelStandard;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}

package org.proto1.domain.valueprovider;

import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.proto1.domain.AbstractEntity;

@Entity
@Table(name="GRADE_OF_STEEL_STANDARD", schema="VALUEPROVIDER")
public class GradeSteelStandard extends AbstractEntity implements ValueProvider {
	
	private String name;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="gradeSteelStandard")
	private List<GradeSteel> grades;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<GradeSteel> getGrades() {
		return grades;
	}

	public void setGrades(List<GradeSteel> grades) {
		this.grades = grades;
	}

	public Map<Long, Object> getValues() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean checkValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

}

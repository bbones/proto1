/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
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

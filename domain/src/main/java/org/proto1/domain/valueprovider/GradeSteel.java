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

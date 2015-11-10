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
package org.proto1.domain.accounting;

import javax.persistence.Entity;

@Entity
public class AnaliticRole {
	private Long Id;
	private String analiticRoleName;
	private String analiticSource;

	public String getAnaliticRoleName() {
		return analiticRoleName;
	}

	public void setAnaliticRoleName(String analiticRoleName) {
		this.analiticRoleName = analiticRoleName;
	}

	public String getAnaliticSource() {
		return analiticSource;
	}

	public void setAnaliticSource(String analiticSource) {
		this.analiticSource = analiticSource;
	}

	@javax.persistence.Id
	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

}

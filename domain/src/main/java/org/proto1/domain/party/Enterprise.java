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
package org.proto1.domain.party;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.proto1.domain.Language;

@Entity
@PrimaryKeyJoinColumn(name="ENTERPRISE_ID")
public class Enterprise extends Party {
	@OneToMany(cascade=CascadeType.ALL, mappedBy="enterprise", fetch = FetchType.EAGER)
	private List<EnterpriseName> enterpriseNames;
	
	private Long eskId;

	public List<EnterpriseName> getEnterpriseNames() {
		return enterpriseNames;
	}

	public void setEnterpriseNames(List<EnterpriseName> enterpriseNames) {
		this.enterpriseNames = enterpriseNames;
	}

	public Long getEskId() {
		return eskId;
	}

	public void setEskId(Long eskId) {
		this.eskId = eskId;
	}

	@Override
	public	String getName(Language language) {
		for (EnterpriseName name : enterpriseNames) {
			if(name.getLanguage().equals(language))
				return name.getName();
		}
		return null;
	}
	
}

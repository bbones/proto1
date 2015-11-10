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
package org.proto1.dto;

import org.proto1.dtotools.DTODecode;

public class EnterpriseNameDTO extends DTO {

	private static final long serialVersionUID = -734027124860323418L;

	private Long enterpriseNameId;
	private Long enterpriseId;
	private String enterpriseName;
	private Long languageId;
	private String languageName;

	@DTODecode(destination="setId")
	public Long getEnterpriseNameId() {
		return enterpriseNameId;
	}

	public void setEnterpriseNameId(Long enterpriseNameId) {
		this.enterpriseNameId = enterpriseNameId;
	}

	@DTODecode(destination="setEnterprise", service="org.proto1.services.party.EnterpriseService", method="get")
	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@DTODecode(destination="setName")
	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	@DTODecode(destination="setLanguage", service="org.proto1.services.LanguageService", method="get")
	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

}

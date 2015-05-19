/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
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

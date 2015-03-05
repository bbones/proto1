/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.dto;

public class EnterpriseNameDTO extends DTO {

	private static final long serialVersionUID = -734027124860323418L;

	private Long enterpriseNameId;
	private Long enterpriseId;
	private String enterpriseName;
	private Long languageId;
	private String languageName;

	public Long getEnterpriseNameId() {
		return enterpriseNameId;
	}

	public void setEnterpriseNameId(Long enterpriseNameId) {
		this.enterpriseNameId = enterpriseNameId;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

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

/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.dto;

public class ParameterNameDTO extends DTO {
	private static final long serialVersionUID = -9049985815355413261L;

	private Long parameterNameId;
	private Long parameterId;
	private String parameterName;
	private Long languageId;
	private String languageName;

	public Long getParameterNameId() {
		return parameterNameId;
	}

	public void setParameterNameId(Long parameterNameId) {
		this.parameterNameId = parameterNameId;
	}

	public Long getParameterId() {
		return parameterId;
	}

	public void setParameterId(Long parameterId) {
		this.parameterId = parameterId;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
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

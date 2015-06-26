package org.proto1.dto;

public class UnitOfMeasurementNameDTO extends DTO {

	private static final long serialVersionUID = -3596609762108126966L;

	private Long nameId;
	private Long id;
	private Long languageId;
	private String shortName;
	private String fullName;

	
	public Long getNameId() {
		return nameId;
	}

	public void setNameId(Long nameId) {
		this.nameId = nameId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

}

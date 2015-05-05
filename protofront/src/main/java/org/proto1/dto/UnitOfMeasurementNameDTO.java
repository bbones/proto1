package org.proto1.dto;

public class UnitOfMeasurementNameDTO extends DTO {

	private static final long serialVersionUID = -3596609762108126966L;

	private Long uomNameId;
	private Long uomId;
	private Long languageId;
	private String shortName;
	private String fullName;

	public Long getUomNameId() {
		return uomNameId;
	}

	public void setUomNameId(Long uomNameId) {
		this.uomNameId = uomNameId;
	}

	public Long getUomId() {
		return uomId;
	}

	public void setUomId(Long uomId) {
		this.uomId = uomId;
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

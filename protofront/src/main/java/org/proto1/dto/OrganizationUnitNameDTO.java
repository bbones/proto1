package org.proto1.dto;

public class OrganizationUnitNameDTO extends DTO {
	private static final long serialVersionUID = -159062963228839721L;

	private Long ounId;
	private Long ouId;
	private String name;
	private Long languageId;

	public Long getOuId() {
		return ouId;
	}

	public void setOuId(Long ouId) {
		this.ouId = ouId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
	}

	public Long getOunId() {
		return ounId;
	}

	public void setOunId(Long ounId) {
		this.ounId = ounId;
	}

}

package org.proto1.dto;

public class OrganizationUnitNameDTO extends DTO {
	private static final long serialVersionUID = -159062963228839721L;

	private Long id;
	private Long orgUnitId;
	private String name;
	private Long languageId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(Long orgUnitId) {
		this.orgUnitId = orgUnitId;
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

}

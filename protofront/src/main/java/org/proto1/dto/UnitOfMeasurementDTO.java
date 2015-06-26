package org.proto1.dto;

public class UnitOfMeasurementDTO extends DTO {

	private static final long serialVersionUID = 6376088966271169163L;

	private Long id;
	private String shortName;
	private String fullName;

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

}

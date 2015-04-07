package org.proto1.dto;

public class UnitOfMeasurementDTO extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6376088966271169163L;

	private Long uomId;
	private String uomName;

	public Long getUomId() {
		return uomId;
	}

	public void setUomId(Long uomId) {
		this.uomId = uomId;
	}

	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

}

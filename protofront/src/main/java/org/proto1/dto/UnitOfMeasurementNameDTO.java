package org.proto1.dto;

public class UnitOfMeasurementNameDTO extends DTO {

	private static final long serialVersionUID = -3596609762108126966L;

	private Long uomNameId;
	private Long uomId;
	private String uomShortName;
	private String uomFullName;

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

	public String getUomShortName() {
		return uomShortName;
	}

	public void setUomShortName(String uomShortName) {
		this.uomShortName = uomShortName;
	}

	public String getUomFullName() {
		return uomFullName;
	}

	public void setUomFullName(String uomFullName) {
		this.uomFullName = uomFullName;
	}

}

package org.proto1.dto;

public class ParameterDTO extends DTO {

	private static final long serialVersionUID = -2660347172603787025L;

	private Long parameterId;
	private String parameterName;
	private String parameterType;

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

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

}

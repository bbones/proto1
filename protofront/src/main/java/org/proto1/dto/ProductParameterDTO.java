package org.proto1.dto;

public class ProductParameterDTO extends DTO {

	private static final long serialVersionUID = -2230010614381916435L;
	
	private Long productParameterId;
	private Long productId;
	private Long parameterId;
	private String parameterName;
	private boolean optional;
	
	public Long getProductParameterId() {
		return productParameterId;
	}
	public void setProductParameterId(Long productParameterId) {
		this.productParameterId = productParameterId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public boolean isOptional() {
		return optional;
	}
	public void setOptional(boolean optional) {
		this.optional = optional;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}

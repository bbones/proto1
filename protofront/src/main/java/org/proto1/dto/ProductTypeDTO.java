package org.proto1.dto;

import java.util.List;

public class ProductTypeDTO extends DTO {
	private static final long serialVersionUID = -1910776023192337434L;

	private Long id;
	private Long parentId;
	private String localizedProductName;
	private List<ProductTypeNameDTO> productTypeNames;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public List<ProductTypeNameDTO> getProductTypeNames() {
		return productTypeNames;
	}

	public void setProductTypeNames(List<ProductTypeNameDTO> productTypeNames) {
		this.productTypeNames = productTypeNames;
	}

	public String getLocalizedProductName() {
		return localizedProductName;
	}

	public void setLocalizedProductName(String localizedProductName) {
		this.localizedProductName = localizedProductName;
	}

}

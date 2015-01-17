package org.proto1.dto;

import java.util.List;

public class ProductTypeDTO extends DTO {
	private static final long serialVersionUID = -1910776023192337434L;

	private Long id;
	private Long parentId;
	private List<ProductTypeNameDTO> namesList;

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
}

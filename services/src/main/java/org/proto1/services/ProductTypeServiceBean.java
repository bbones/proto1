package org.proto1.services;

import java.util.List;

import org.proto1.domain.product.ProductType;
import org.proto1.repository.ProductTypeRepository;

public class ProductTypeServiceBean implements ProductTypeService {
	private ProductTypeRepository productTypeRepository;

	public ProductType getNodeById(Long id) {
		return productTypeRepository.getById(id);
	}

	public void setProductTypeRepository(
			ProductTypeRepository productTypeRepository) {
		this.productTypeRepository = productTypeRepository;
	}

	public List<ProductType> getChildByParentId(Long id) {
		
		return productTypeRepository.getByParentTypeId(id);
	}

}

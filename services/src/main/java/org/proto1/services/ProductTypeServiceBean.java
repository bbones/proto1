package org.proto1.services;

import java.util.List;

import org.proto1.domain.product.ProductType;
import org.proto1.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductTypeServiceBean implements ProductTypeService {
	@Autowired
	private ProductTypeRepository productTypeRepository;

	public ProductType getNodeById(Long id) {
		return productTypeRepository.getById(id);
	}

	public void setProductTypeRepository(
			ProductTypeRepository productTypeRepository) {
		this.productTypeRepository = productTypeRepository;
	}

	public List<ProductType> getByParentTypeId(Long id) {
		return productTypeRepository.getByParentTypeId(id);
	}

}

package org.proto1.services;

import java.util.List;

import org.proto1.domain.product.ProductType;
import org.proto1.repository.ProductTypeRepository;

public interface ProductTypeService {

	ProductType getNodeById(Long id);
	List<ProductType> getByParentTypeId(Long id);

	void setProductTypeRepository(ProductTypeRepository productTypeRepository);

}

package org.proto1.services.product;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.ProductType;
import org.proto1.domain.product.ProductTypeName;
import org.proto1.repository.ProductTypeRepository;

public interface ProductTypeService {

	ProductType getNodeById(Long id);
	List<ProductType> getByParentTypeId(Long id);
	List<Map<String, Object>> getByParentTypeIdByLanguageId(Long parentId, Long languageId);
	
	void setProductTypeRepository(ProductTypeRepository productTypeRepository);
	int countChild(Long parentId);
	List<ProductTypeName> getNames(Long id);
	
	ProductType save(ProductType productType);
	void deleteProductTypeById(Long id);
	void saveProductTypeName(Long id, Long productTypeId, Long languageId, String productTypeName);

}

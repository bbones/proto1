package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.Product;
import org.proto1.domain.product.ProductName;
import org.proto1.domain.product.ProductParameter;
import org.proto1.repository.LanguageRepository;
import org.proto1.repository.ProductRepository;

public interface ProductService {

	void setProductRepository(ProductRepository productRepository);

	Product getById(Long id);

	Product save(Product product);

	void delete(Long id);

	void saveProductNames(Long productId,  List<ProductName> productNames);
	
	void saveProductName(Long productId, Long languageId, String productNames);

	void setLanguageRepository(LanguageRepository languageRepository);

	void deleteName(Long productId, Long languageId);

	List<Map<String, Object>> getListByProdTypeIdByLanguageId(
			Long productTypeId, Long languageId);

	List<ProductName> getNamesList(Long productId);
	
	List<Map<String, java.lang.Object>> getParameterList(Long productId, Long languageId);

	ProductParameter saveProductParameter(Long productId, Long parameterId, Boolean required);

}

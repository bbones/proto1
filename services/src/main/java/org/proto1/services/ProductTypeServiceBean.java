package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.ProductType;
import org.proto1.domain.product.ProductTypeName;
import org.proto1.repository.LanguageRepository;
import org.proto1.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductTypeServiceBean implements ProductTypeService {
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Autowired
	private LanguageRepository languageRepository;

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

	public List<Map<String, Object>> getByParentTypeIdByLanguageId(
			Long parentId, Long languageId) {
		if (parentId == null)
			return productTypeRepository.getRootParentTypesLanguageId(languageId);
		else			
			return productTypeRepository.getByParentTypeIdLanguageId(parentId, languageId);
	}

	public int countChild(Long parentId) {
		return productTypeRepository.countChild(parentId);
	}

	public List<ProductTypeName> getNames(Long id) {
		return productTypeRepository.getById(id).getProductTypeNames();
	}

	@Transactional
	public ProductType save(ProductType productType) {
		return productTypeRepository.save(productType);
	}

	@Transactional
	public void deleteProductTypeById(Long id) {
		productTypeRepository.delete(id);
	}

	public void saveName(Long productTypeId, Long languageId,
			String productTypeName) {
		productTypeRepository.updateName()
		
	}

}

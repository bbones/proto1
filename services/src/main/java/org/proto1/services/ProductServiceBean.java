package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.proto1.domain.product.Product;
import org.proto1.domain.product.ProductName;
import org.proto1.domain.product.ProductParameter;
import org.proto1.repository.LanguageRepository;
import org.proto1.repository.ParameterRepository;
import org.proto1.repository.ProductNameRepository;
import org.proto1.repository.ProductParameterRepository;
import org.proto1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceBean implements ProductService {
	final static Logger logger = Logger.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductNameRepository productNameRepository;
	
	@Autowired
	private ParameterRepository parameterRepository;
	
	@Autowired
	private ProductParameterRepository productParameterRepository;

	@Autowired
	private LanguageRepository languageRepository;

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public void setLanguageRepository(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
		
	}
	
	public Product getById(Long id) {
		Product product = productRepository.getById(id);
		return product;
	}

	@Transactional
	public Product save(Product product) {
		return productRepository.save(product);
	}

	public void delete(Long id) {
		productRepository.delete(id);
	}

	public List<Map<String, Object>> getListByProdTypeIdByLanguageId(
			Long productTypeId, Long languageId) {
		return productRepository.getListByProdTypeIdByLanguageId(productTypeId, languageId);
	}

	public ProductName saveProductName(Long productNameId, Long productId,
			Long languageId, String productNames) {
		ProductName productName = new ProductName();
		productName.setId(productNameId);
		productName.setProduct(productRepository.findOne(productId));
		productName.setLanguage(languageRepository.findOne(languageId));
		productName.setName(productNames);
		productNameRepository.save(productName);
		
		return productName;
	}

	public void deleteName(Long productNameId) {
		productNameRepository.delete(productNameId);
		
	}

	public List<ProductName> getNamesList(Long productId) {
		return productNameRepository.findByProductId(productId);
	}
	
	public List<Map<String, java.lang.Object>> getParameterList(Long productId, Long languageId) {
		return productParameterRepository.getParametersByProductIdLanguageId(productId, languageId);
	}

	@Transactional
	public ProductParameter saveProductParameter(Long productId, Long parameterId, Boolean required) {
		ProductParameter productParameter = new ProductParameter();
		productParameter.setProduct(productRepository.findOne(productId));
		productParameter.setParameter(parameterRepository.findOne(parameterId));
		productParameter.setRequired(required);
		
		return productParameterRepository.save(productParameter);
	}

	@Transactional
	public void deleteProductParameter(Long productParameterId) {
		productParameterRepository.delete(productParameterId);
	}


}

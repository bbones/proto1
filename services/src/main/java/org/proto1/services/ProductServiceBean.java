package org.proto1.services;

import org.apache.log4j.Logger;
import org.proto1.domain.product.Product;
import org.proto1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductServiceBean implements ProductService {
	final static Logger logger = Logger.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional
	public Product getById(Long id) {
		Product product = productRepository.findOne(id);
		logger.debug("Size->" + product.getProductNames().size());
		return product;
	}

	public Product save(Product product) {
		return productRepository.save(product);
	}

	public void delete(Long id) {
		productRepository.delete(id);
	}
	
	
}

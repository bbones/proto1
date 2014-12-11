package org.proto1.services;

import org.proto1.domain.product.Product;
import org.proto1.repository.ProductRepository;

public interface ProductService {

	void setProductRepository(ProductRepository productRepository);

	Product getById(Long id);

	Product save(Product product);

	void delete(Long id);

}

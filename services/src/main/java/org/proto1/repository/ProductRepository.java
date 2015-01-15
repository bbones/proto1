package org.proto1.repository;

import org.proto1.domain.product.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
	@EntityGraph(value="Product.productNames", type=EntityGraphType.LOAD)
	public Product getById(Long id);
}

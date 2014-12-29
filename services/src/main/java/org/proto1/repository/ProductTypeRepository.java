package org.proto1.repository;

import java.util.List;

import org.proto1.domain.product.ProductType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long>{

	@EntityGraph(value="ProductType.productTypeNames", type=EntityGraphType.LOAD)
	public ProductType getById(Long id);
	
	public List<ProductType> getByParentTypeId(Long id);

}

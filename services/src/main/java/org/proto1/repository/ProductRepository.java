package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {
	@EntityGraph(value="Product.productNames", type=EntityGraphType.LOAD)
	public Product getById(Long id);
	
	@Query("select new Map(p.id as id, p.productType.id as productTypeId , pn.name as name) " + 
			"from Product p join p.productNames pn " + 
			"where p.productType.id = :pt_id and pn.language.id = :language_id")
	public List<Map<String, Object>> getListByProdTypeIdByLanguageId(@Param("pt_id") Long productTypeId, @Param("language_id") Long languageId);
	
}

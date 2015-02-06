package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.product.ProductParameter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductParameterRepository extends CrudRepository<ProductParameter, Long> {

	List<ProductParameter> getParameterByProductId(Long productId);
	@Query("select new Map(pp.id as prodParamId, pp.product.id as prodId, pp.parameter.id as parameterId, pp.required as required, ppn.name as parameterName) "
			+ "from ProductParameter pp join pp.parameter.parameterNames ppn "
			+ "where pp.product.id = :product_id and ppn.language.id=:language_id")
	List<Map<String, Object>> getParametersByProductIdLanguageId(@Param("product_id") Long productId, @Param("language_id") Long languageId);
	

}

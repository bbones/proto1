package org.proto1.repository;

import org.proto1.domain.Person;
import org.proto1.domain.product.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long> {
	@Query("from Product p  join fetch p.productNames names where p.id = (:id)")
	public Person findByIdAndFetchNamesEagerly(@Param("id") Long id);
}

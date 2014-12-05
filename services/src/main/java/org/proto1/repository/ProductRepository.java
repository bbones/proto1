package org.proto1.repository;

import org.springframework.data.repository.CrudRepository;
import org.proto1.domain.order.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}

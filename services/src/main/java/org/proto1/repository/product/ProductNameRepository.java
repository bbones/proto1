/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository.product;

import java.util.List;

import org.proto1.domain.product.ProductName;
import org.springframework.data.repository.CrudRepository;

public interface ProductNameRepository extends CrudRepository<ProductName, Long>{
	
	List<ProductName> findByProductId(Long productId);

}

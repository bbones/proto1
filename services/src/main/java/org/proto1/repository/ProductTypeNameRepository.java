/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import org.proto1.domain.product.ProductTypeName;
import org.springframework.data.repository.CrudRepository;

public interface ProductTypeNameRepository extends CrudRepository<ProductTypeName, Long> {

}

/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import org.springframework.data.repository.CrudRepository;

import org.proto1.domain.ContractSide;

public interface ContractSideRepository extends CrudRepository<ContractSide, Long> {

}

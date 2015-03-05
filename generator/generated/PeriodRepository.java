/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package com.proto1.server.repository;

import org.springframework.data.repository.CrudRepository;

import org.proto1.domain.Period;

public interface PeriodRepository extends CrudRepository<Period, Long> {

}

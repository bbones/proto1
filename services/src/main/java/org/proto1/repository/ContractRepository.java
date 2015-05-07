/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.proto1.domain.Contract;

public interface ContractRepository extends CrudRepository<Contract, Long> {
	@Query("select new Map(c.id as id, c.documentNo as documentNo, c.issueDate as issueDate) from Contract c")
	public List<Map<String, Object>> list();
}

/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
/**
 * ContractSupplementRepository.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 28, 2015
 */
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.proto1.domain.ContractSupplement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface ContractSupplementRepository extends CrudRepository<ContractSupplement, Long> {
	@Query("select new Map( cs.id as id, cs.documentNo as documentNo, cs.issueDate as issueDate, cur.numCode as numCode, cur.charCode as currencyCode) " 
			+ "from ContractSupplement cs join cs.currency cur "
			+ "where cs.contract.id=:contract_id")
	public List<Map<String, Object>> list(@Param("contract_id") Long contractId);

}

/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.proto1.domain.ContractSide;

public interface ContractSideRepository extends CrudRepository<ContractSide, Long> {

	/**
	 * @param contractId
	 * @return
	 */
	@Query("select new Map(cs.id as csId, sr.id as roleId, cs.party.id as partyId, "
			+ "coalesce(en.name, pn.lastName || ' ' || pn.firstName) as partyName) "
			+ "from ContractSide cs join cs.sideRole sr " 
			+ "left join cs.party.enterpriseNames en "
			+ "left join cs.party.personNames pn "
			+ "where cs.contract.id=:contract_id and (en.language.id=:language_id or pn.language.id=:language_id)")
	List<Map<String, Object>> list(@Param("contract_id") Long contractId, @Param("language_id") Long languageId);

}

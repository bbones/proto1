/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
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

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
 * PartyRepository.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created May 12, 2015
 */
package org.proto1.repository.party;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.Party;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface PartyRepository extends PagingAndSortingRepository<Party, Long> {
	@Query("select new Map(p.id as partyId, "
			+ "coalesce(en.name, pn.lastName || ' ' ||  pn.firstName) as partyName) "
			+ "from Party p "
			+ "left outer join p.enterpriseNames en "
			+ "left outer join p.personNames pn "
			+ "where (en.language.id=:language_id or pn.language.id=:language_id) and (pn.lastName like :srch or en.name like :srch)")
	public List<Map<String, Object>> partyList(@Param("language_id") Long languageId, @Param("srch") String searchStr, Pageable p);

	@Query("select count(p)"
			+ "from Party p "
			+ "left join p.enterpriseNames en "
			+ "left join p.personNames pn "
			+ "where (en.language.id=:language_id or pn.language.id=:language_id) and (pn.lastName like :srch or en.name like :srch)")
	public Long getPartyCounter(@Param("language_id") Long languageId, @Param("srch") String searchStr);

}

	//select new Map(p.id as partyId, 
	//coalesce(en.name, pn.lastName || ' ' ||  pn.firstName) as partyName) 
	//from Party p 
	//left outer join p.enterpriseNames en 
	//left outer join p.personNames pn
	//where (en.language.id=:language_id or pn.language.id=:language_id) and 
	//(pn.lastName like :srch or en.name like :srch)


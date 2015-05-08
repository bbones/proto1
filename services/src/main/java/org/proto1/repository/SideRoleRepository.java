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
import org.proto1.domain.SideRole;

public interface SideRoleRepository extends CrudRepository<SideRole, Long> {
	@Query("select sr from SideRole sr join sr.sideRoleNames srn where srn.name = :role_name") 
	SideRole getBySideRoleName(@Param(value = "role_name") String name);
	
	@Query("select new Map(sr.id as srId, srn.name as srName) from SideRole sr join sr.sideRoleNames srn "
			+ "where srn.language.id = :language_id")
	List<Map<String, Object>> getList(@Param("language_id") Long languageId);
}

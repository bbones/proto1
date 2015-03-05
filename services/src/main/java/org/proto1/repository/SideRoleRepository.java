/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.proto1.domain.SideRole;

public interface SideRoleRepository extends CrudRepository<SideRole, Long> {
	@Query("select sr from SideRole sr join sr.sideRoleNames srn where srn.name = :role_name") 
	SideRole getBySideRoleName(@Param(value = "role_name") String name);
}

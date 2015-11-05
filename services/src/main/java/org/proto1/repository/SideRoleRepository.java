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
import org.proto1.domain.SideRole;

public interface SideRoleRepository extends CrudRepository<SideRole, Long> {
	@Query("select sr from SideRole sr join sr.sideRoleNames srn where srn.name = :role_name") 
	SideRole getBySideRoleName(@Param(value = "role_name") String name);
	
	@Query("select new Map(sr.id as srId, srn.name as srName) from SideRole sr join sr.sideRoleNames srn "
			+ "where srn.language.id = :language_id")
	List<Map<String, Object>> getList(@Param("language_id") Long languageId);
}

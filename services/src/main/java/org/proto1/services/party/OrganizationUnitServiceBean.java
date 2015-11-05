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
package org.proto1.services.party;

import java.util.List;
import java.util.Map;

import org.proto1.domain.party.OrganizationUnit;
import org.proto1.domain.party.OrganizationUnitName;
import org.proto1.repository.party.OrganizationUnitNameRepository;
import org.proto1.repository.party.OrganizationUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationUnitServiceBean implements OrganizationUnitService {
	
	@Autowired
	OrganizationUnitRepository organizationUnitRepository;
	
	@Autowired
	OrganizationUnitNameRepository organizationUnitNameRepository;

	public OrganizationUnit get(Long organizatioUnitId) {
		return organizationUnitRepository.findOne(organizatioUnitId);
	}

	public OrganizationUnit save(OrganizationUnit organizationUnit) {
		return organizationUnitRepository.save(organizationUnit);
	}

	public void delete(Long organizatioUnitId) {
		organizationUnitRepository.delete(organizatioUnitId);
	}

	public List<OrganizationUnitName> getNamesList(Long id) {
		return organizationUnitNameRepository.getByOrganizationUnitId(id);
	}

	public List<Map<String, Object>> getList(Long enterpriseId, Long languageId) {
		return organizationUnitRepository.getList(enterpriseId, languageId);
	}

	public OrganizationUnitName saveName(OrganizationUnitName oun) {
		return organizationUnitNameRepository.save(oun);
	}

	public void deleteName(Long id) {
		organizationUnitNameRepository.delete(id);
	}
}

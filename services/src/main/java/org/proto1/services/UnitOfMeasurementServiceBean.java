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
package org.proto1.services;

import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.proto1.domain.UnitOfMeasurement;
import org.proto1.domain.UnitOfMeasurementName;
import org.proto1.repository.UnitOfMeasurementNameRepository;
import org.proto1.repository.UnitOfMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UnitOfMeasurementServiceBean implements UnitOfMeasurementService {

	@Autowired
	UnitOfMeasurementRepository uomRepository;
	
	@Autowired
	UnitOfMeasurementNameRepository uomNameRepository;

	@Autowired
	RuntimeService runtimeService;
	
	public UnitOfMeasurement get(Long id) {
		return uomRepository.findOne(id);
	}

	public List<Map<String, Object>> getUOMList(Long languageId) {
		return uomRepository.getList(languageId);
	}

	public UnitOfMeasurement save(UnitOfMeasurement uom) {
		return uomRepository.save(uom);
	}

	public void delete(Long id) {
		uomRepository.delete(id);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.UnitOfMeasurementService#deleteName(java.lang.Long)
	 */
	public void deleteName(Long nameId) {
		uomNameRepository.delete(nameId);
	}

	/* (non-Javadoc)
	 * @see org.proto1.services.UnitOfMeasurementService#saveName(org.proto1.domain.UnitOfMeasurementName)
	 */
	public UnitOfMeasurementName saveName(UnitOfMeasurementName uomName) {
		return uomNameRepository.save(uomName);
	}

	public List<UnitOfMeasurementName> getUOMNamesList(Long uomId) {
		return uomNameRepository.findByUnitOfMeasurementId(uomId);
	}

}

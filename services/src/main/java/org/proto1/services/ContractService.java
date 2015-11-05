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

import org.proto1.domain.Contract;
import org.proto1.domain.ContractSide;
import org.proto1.domain.ContractSupplement;
import org.proto1.domain.SideRole;
import org.proto1.repository.ContractRepository;

public interface ContractService {

	List<Map<String, Object>> getContracts();

	void setContractRepository(ContractRepository crep);

	Contract getContract(Long id);

	Contract save(Contract contract);

//	List<Map<String, Object>> findAll(Contract criteria);
	List<Contract> findAll(Contract criteria);

	void delete(Long id);

	/**
	 * @param contractId
	 * @return
	 */
	List<Map<String, Object>> getSupplements(Long contractId);

	/**
	 * @param contractId
	 * @return
	 */
	List<Map<String, Object>> getSides(Long contractId, Long languageId);

	/**
	 * @param languageId
	 * @return
	 */
	List<Map<String, Object>> getRoles(Long languageId);

	SideRole getRole(Long id);

	ContractSide saveSide(ContractSide contractSide);

	void deleteSide(Long id);

	ContractSupplement getSupplement(Long supplementId);

	ContractSupplement saveSupplement(ContractSupplement cs);

	void deleteSupplement(Long supplementId);

}

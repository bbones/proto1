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
/**
 * ContractSupplementServiceBean.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 28, 2015
 */
package org.proto1.services;

import org.springframework.stereotype.Service;

import org.proto1.domain.ContractSupplement;
import org.proto1.repository.ContractSupplementRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */

@Service
public class ContractSupplementServiceBean implements ContractSupplementService {

	@Autowired
	ContractSupplementRepository contractSupplementRepository;
	
	/* (non-Javadoc)
	 * @see org.proto1.services.ContractSupplentService#get(long)
	 */
	public ContractSupplement get(long id) {
		return contractSupplementRepository.findOne(id);
	}

}

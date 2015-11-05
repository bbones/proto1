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

import org.proto1.domain.SideRole;
import org.proto1.repository.SideRoleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationConstantsBean implements ApplicationConstants, InitializingBean {
	@Autowired
	SideRoleRepository sideRoleRepository;
	
	private SideRole buyerSideRole, sellerSideRole;
	
	public SideRole getDefaultBuyerRole() {
		return buyerSideRole;
	}

	public void afterPropertiesSet() throws Exception {

		buyerSideRole = sideRoleRepository.getBySideRoleName("Buyer");
		sellerSideRole = sideRoleRepository.getBySideRoleName("Seller");
	}

	public SideRole getDefaultSellerRole() {
		return sellerSideRole;
	}

}

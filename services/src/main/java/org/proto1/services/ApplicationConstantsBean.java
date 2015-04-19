/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
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

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
	
	private SideRole sideRole;
	
	public SideRole getDefaultBuyerRole() {
		return sideRole;
	}

	public void afterPropertiesSet() throws Exception {

		sideRole = sideRoleRepository.getBySideRoleName("Buyer");
	}

}

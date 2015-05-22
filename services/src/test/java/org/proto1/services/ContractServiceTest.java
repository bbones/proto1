/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
 *******************************************************************************/
package org.proto1.services;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.proto1.domain.Contract;
import org.proto1.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/domain.xml"})
public class ContractServiceTest extends AbstractJUnit4SpringContextTests {

	ContractService contractService = new ContractServiceBean();

	@Autowired
	private Contract contract;

	@Test
	public void testGetEnterpriseById() {
		ContractRepository crep = createMock(ContractRepository.class);
		expect(crep.findOne(1L)).andReturn(contract);
		replay(crep);
		contractService.setContractRepository(crep);
		assertEquals(contract, contractService.getContract(1L));
	}
	
	@Test
	public void testSave() {
		ContractRepository crep = createMock(ContractRepository.class);
		expect(crep.save(contract)).andReturn(contract);
		replay(crep);
		contractService.setContractRepository(crep);
		assertEquals(contract, contractService.save(contract));
	}
	
	@Test
	public void testDelete() {
		ContractRepository crep = createMock(ContractRepository.class);
		crep.delete(contract.getId());
		replay(crep);
		contractService.setContractRepository(crep);
		contractService.delete(contract.getId());
		
	}

}

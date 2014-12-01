package org.proto1.services;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.proto1.domain.Contract;
import org.proto1.domain.Enterprise;
import org.proto1.repository.ContractRepository;
import org.proto1.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations={"file:src/main/resources/domain.xml"})
public class ContractServiceTest {

	ContractService contractService = new ContractServiceBean();

	@Autowired
	private Contract contract;

	@Test
	public void testGetEnterpriseById() {
		ContractRepository crep = createMock(ContractRepository.class);
		expect(crep.findOne(1L)).andReturn(contract);
		replay(crep);
		contractService.setContractRepository(crep);
		assertEquals(contract, contractService.getContractById(1L));
	}
	
	@Test
	public void testSave() {
		ContractRepository erep = createMock(ContractRepository.class);
		expect(erep.save(contract)).andReturn(contract);
		replay(erep);
		contractService.setContractRepository(erep);
		assertEquals(contract, contractService.save(contract));
	}
	
	@Test
	public void testDelete() {
		EnterpriseRepository erep = createMock(EnterpriseRepository.class);
		erep.delete(isd.getId());
		replay(erep);
		enterpriseService.setEnterpriseRepository(erep);
		enterpriseService.delete(isd.getId());
		
	}

}

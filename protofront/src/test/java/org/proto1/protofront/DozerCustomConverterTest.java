// TODO Debug instantiation - call stack


package org.proto1.protofront;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.proto1.domain.Contract;
import org.proto1.domain.ContractSupplement;
import org.proto1.domain.Currency;
import org.proto1.dto.ContractDTO;
import org.proto1.dto.ContractSupplementDTO;
import org.proto1.mapper.CurrencyConverter;
import org.proto1.services.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration(locations={"classpath:/META-INF/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DozerCustomConverterTest extends AbstractJUnit4SpringContextTests {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	Mapper mapper;

	@Autowired
	ApplicationContext ac;
	
	@Test
	public void testCurrency() {
		
		String[] beanList =	ac.getBeanNamesForType(MasterDataService.class);
		
		for(String name : beanList) {
			logger.debug(name);
		}
		
		beanList =	ac.getBeanNamesForType(CustomConverter.class);
		
		for(String name : beanList) {
			logger.debug(name);
		}
		
		logger.debug(mapper.getClass().getName());
		
		for(CustomConverter cc : ((org.dozer.DozerBeanMapper)mapper).getCustomConverters()) {
			logger.debug("CC->" + cc.getClass().getName());
		}
		
		Currency cur = mapper.map(840, Currency.class);

		logger.debug(cur.getNumCode());
		logger.debug(cur.getCharCode());
		logger.debug(cur.getSign());
		
	}
	
	@Test
	public void testContractSupplementMapping() {
		ContractSupplementDTO csDTO = new ContractSupplementDTO();
		csDTO.setId(25L);
		csDTO.setContractId(65L);
		csDTO.setCurrencyId(840);
		csDTO.setIssueDate(new Date());
		
		ContractSupplement cs = mapper.map(csDTO, ContractSupplement.class);
		
		assertEquals(csDTO.getId(), cs.getId());
		assertEquals(csDTO.getCurrencyId(), cs.getCurrency().getNumCode());
		assertEquals("USD", cs.getCurrency().getCharCode());
		
		assertEquals(csDTO.getContractId(), cs.getContract().getId());
		assertEquals(csDTO.getIssueDate(), cs.getIssueDate());
		
	}

	@Test
	public void testInheritance() {
		Contract contract = new Contract();
		contract.setDocumentNo("DOCNO");
		ContractDTO cDTO = mapper.map(contract, ContractDTO.class);
		assertEquals(contract.getDocumentNo(), cDTO.getDocumentNo());
	}
}

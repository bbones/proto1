package org.proto1.domain.accounting;

import static org.junit.Assert.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.proto1.domain.Analitic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={
		"file:src/main/resources/META-INF/domain.xml",
		"file:src/main/resources/META-INF/accounting.xml"})
public class AccountingDomainTest extends AbstractJUnit4SpringContextTests {
	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	AnaliticRole main_contract_role;
	
	@Autowired
	Analitic contract_analitic;

	@Test
	public void test() {
		assertNotNull(main_contract_role);
		assertNotNull(contract_analitic);
		assertEquals("org.proto1.domain.Contract", contract_analitic.getAnaliticClass().getCanonicalName());
	}

}

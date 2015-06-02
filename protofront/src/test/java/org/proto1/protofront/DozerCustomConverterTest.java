package org.proto1.protofront;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.junit.Test;
import org.proto1.domain.Currency;
import org.proto1.mapper.CurrencyConverter;
import org.proto1.services.MasterDataService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;


@ContextConfiguration(locations={"classpath:/META-INF/applicationContext.xml"})
public class DozerCustomConverterTest extends AbstractJUnit4SpringContextTests {
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	org.dozer.Mapper mapper;

	@Autowired
	ApplicationContext ac;

	@Test
	public void test() {
		
		String[] beanList =	ac.getBeanNamesForType(MasterDataService.class);
		
		for(String name : beanList) {
			logger.debug(name);
		}
		
		beanList =	ac.getBeanNamesForType(CurrencyConverter.class);
		
		for(String name : beanList) {
			logger.debug(name);
		}
		
		Currency cur = mapper.map(980, Currency.class);
		logger.debug(cur.getNumCode());
		logger.debug(cur.getCharCode());
		logger.debug(cur.getSign());

	}

}

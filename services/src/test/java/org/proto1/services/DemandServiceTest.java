package org.proto1.services;

import static org.junit.Assert.*;
import static org.easymock.EasyMock.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;
import org.proto1.domain.order.OrderLine;
import org.proto1.domain.product.Product;
import org.proto1.repository.ProductRepository;
import org.proto1.repository.order.OrderLineRepository;
import org.proto1.services.order.DemandService;
import org.proto1.services.order.DemandServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/domain.xml", "classpath:/META-INF/product.xml", "classpath:/META-INF/order.xml", "classpath:/META-INF/applicationContext.xml"})
public class DemandServiceTest extends AbstractJUnit4SpringContextTests {
	
	@Autowired
	DemandService demandService;


	@Test
	@Ignore
	public void testGetConsolidatedDemand1() {
		Long[] pl = {3L};
		demandService.getConsolidatedDemand(1L, 5L, pl);
	}

	@Test
	public void testGetConsolidatedDemand2() {
		Long[] pl = {4L,3L, 2L, 1L};
		demandService.getConsolidatedDemand(1L, 5L, pl);
	}

	@Test
	@Ignore
	public void testGetConsolidatedDemand3() {
		Long[] pl = {4L};
		demandService.getConsolidatedDemand(1L, 5L, pl);
	}

}

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

import org.junit.Ignore;
import org.junit.Test;
import org.proto1.services.order.DemandService;
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

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

import static org.easymock.EasyMock.*;
//import static org.junit.Assert.*;

import org.junit.Test;
import org.proto1.domain.Railway;
import org.proto1.repository.RailwayRepository;
import org.proto1.services.RailwayService;
import org.proto1.services.RailwayServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"classpath:/META-INF/railway.xml"})
public class RailwayServiceTest extends AbstractJUnit4SpringContextTests {
	
	RailwayService railwayService = new RailwayServiceBean();
	
	@Autowired
	private Railway rw43;
	
	@Test
	public void testSave() {
		RailwayRepository erep = createMock(RailwayRepository.class);
		expect(erep.save(rw43)).andReturn(rw43);
		replay(erep);
		//railwayService.setRailwayRepository(erep);
		//assertEquals(rw43, railwayService.save(rw43));
	}

}

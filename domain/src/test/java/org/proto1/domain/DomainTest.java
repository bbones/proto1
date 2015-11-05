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
package org.proto1.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.proto1.domain.party.Enterprise;
import org.proto1.domain.party.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"file:src/main/resources/META-INF/domain.xml"})
public class DomainTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	Person pva;
	
	@Autowired
	Enterprise isd;
	
	@Autowired
	Contract contract;
	
	@Test
	public void testParty() {
		assertNotNull(pva);
		assertNotNull(isd);
		assertNotNull(contract);
	}
	
	@Test
	public void testContract() {
		
	}

}

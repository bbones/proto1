/*******************************************************************************
 * Copyright (C) 2015   Valentin Pogrebinsky 
 *
 * mail:pva@isd.com.ua
 * https://github.com/bbones
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
 * GNU v2 license text in root directory of project
 *******************************************************************************/
package org.proto1.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class HashMapTest {
	protected final Log logger = LogFactory.getLog(getClass());
	HashMap<ArrayList<String>, Double> hashMap = new HashMap<ArrayList<String>, Double>();

	@Test
	public void test() {
		hashMap.put(new ArrayList<String>(Arrays.asList("1","2","3")), 0.0);
		
		hashMap.put(new ArrayList<String>(Arrays.asList("2","3")), 0.0);
		
		hashMap.put(new ArrayList<String>(Arrays.asList("1","3")), 0.0);

		hashMap.put(new ArrayList<String>(Arrays.asList("3","1")), 0.0);

		printMap();

		addSet(new ArrayList<String>(Arrays.asList("2","3")), 50.0);

		addSet(new ArrayList<String>(Arrays.asList("1","3")), 20.0);

		addSet(new ArrayList<String>(Arrays.asList("1","3")), 100.0);

		addSet(new ArrayList<String>(Arrays.asList("3","1")), 200.0);

		printMap();
		
	}

	private void printMap() {
		for(Map.Entry<ArrayList<String>, Double> entry : hashMap.entrySet()) {
			logger.debug(entry.getKey() + "->" + entry.getValue());
		}
		
	}

	private void addSet(ArrayList<String> hashSet, Double balance) {
		hashMap.put(hashSet, balance + hashMap.get(hashSet));
	}

}

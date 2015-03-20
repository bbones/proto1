/*******************************************************************************
 * Copyright (c) 2015 Valentin Pogrebinsky
 * All rights reserved. 
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

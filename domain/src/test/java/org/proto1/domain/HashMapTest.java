package org.proto1.domain;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

public class HashMapTest {
	protected final Log logger = LogFactory.getLog(getClass());

	@Test
	public void test() {
		HashMap<Set<Integer>, Double> hashMap = new HashMap<Set<Integer>, Double>();
		logger.debug(hashMap.hashCode());
		
		hashMap.put(new HashSet<Integer>(Arrays.asList(1,2,3)), 100.0);
		logger.debug(hashMap.hashCode());
		
		hashMap.put(new HashSet<Integer>(Arrays.asList(2,3)), 100.0);
		logger.debug(hashMap.hashCode());
		
		hashMap.put(new HashSet<Integer>(Arrays.asList(1,3)), 100.0);
		logger.debug(hashMap.hashCode());
	}

}

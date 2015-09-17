package org.proto1.generator;

import org.junit.Test;
import org.proto1.domain.Railway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(locations = { "classpath:/META-INF/domain.xml" })
public class EntityFromJsonGeneratorTest extends
		AbstractJUnit4SpringContextTests {

	@Autowired
	private ApplicationContext context;

	public static Railway[] toJavaObject(File baseFile) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(baseFile, Railway[].class);
	}

	@Test
	public void test() {
		try {
			Resource[] mappingFiles = ((ResourcePatternResolver) context)
					.getResources("classpath:*.json");
			for (int k = 0; k < mappingFiles.length; k++) {
				System.out.println("filename: "	+ mappingFiles[k]);

				Railway[] railway = toJavaObject(mappingFiles[k].getFile());
				for (int i = 0; i < railway.length; i++) {
					System.out.println("RailwayCode: "
							+ railway[i].getRailwayCode());
				}

				// System.out.println(railway[1].getRailwayCode());

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

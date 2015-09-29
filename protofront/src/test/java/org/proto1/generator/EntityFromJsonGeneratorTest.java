package org.proto1.generator;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.proto1.config.AppConfig;
import org.proto1.config.PersistenceConfig;
import org.proto1.config.WebConfig;
import org.proto1.domain.Language;
import org.proto1.domain.Railway;
import org.proto1.domain.RailwayName;
import org.proto1.services.LanguageService;
import org.proto1.services.RailwayService;
import org.proto1.services.RailwayNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

//@ContextConfiguration(locations = { "classpath:/META-INF/domain.xml" })

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, PersistenceConfig.class,  WebConfig.class })
@WebAppConfiguration
@TransactionConfiguration(defaultRollback = false)
@Transactional
public class EntityFromJsonGeneratorTest {
	@Autowired
    private ApplicationContext context;	
	@Autowired		
	RailwayService railwayService;
	@Autowired		
	RailwayNameService railwayNameService;
	@Autowired
	LanguageService languageService;

	public static Railway[] railwayToJavaObject(File baseFile)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(baseFile, Railway[].class);
	}

	public static RailwayName[] railwayNameToJavaObject(File baseFile)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(baseFile, RailwayName[].class);
	}

	@Test
	public void test() {
		try {
			Resource[] mappingFiles = ((ResourcePatternResolver) context)
					.getResources("classpath:*.json");
			System.out.println("classpath:*.json count:" + mappingFiles.length);
			for (int k = 0; k < mappingFiles.length; k++) {
				System.out
						.println("filename: " + mappingFiles[k].getFilename());

				String fName = mappingFiles[k].getFilename();
				switch (fName) {
				case "railway.json":
					Railway[] railways = railwayToJavaObject(mappingFiles[k]
							.getFile());
					for (int i = 0; i < railways.length; i++) {
						System.out.println("RailwayCode: "
								+ railways[i].getRailwayCode());
						railwayService.save(railways[i]);
					}
					;
					break;
				case "railwayNames.json":
					RailwayName[] railwayName = railwayNameToJavaObject(mappingFiles[k]
							.getFile());
					Railway railway;
					Language language;
					for (int i = 0; i < railwayName.length; i++) {
						railway = railwayService
								.getByRailwayCode(railwayName[i].getRailway()
										.getRailwayCode());
						language = languageService.get(railwayName[i].getLanguage().getId());
						System.out.println(
								"RailwayShortName: " + railwayName[i].getShortName()
								+ " lang: " + language.getName()
								+ " lang_version: " + language.getVersion()
								+ " RailwayCode: " + railwayName[i].getRailway().getRailwayCode()
								+ " RailwayId: " + railway.getId());
						railwayName[i].setRailway(railway);
						railwayName[i].setLanguage(language);
						railwayNameService.save(railwayName[i]);
					}
					;
					break;
				default:
					;
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

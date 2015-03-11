package protofront;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.proto1.domain.Language;
import org.proto1.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = { 
		"classpath:applicationContext.xml" 
	})
public class JacksonTest {
	@Autowired
	LanguageRepository langrep;

	@Test
	public void test() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Language> langlist = (List<Language>) langrep.findAll();
		mapper.writeValue(new File("/Users/bbones/java/language.json"), langlist);	
	}

}

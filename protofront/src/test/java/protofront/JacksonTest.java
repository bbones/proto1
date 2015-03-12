package protofront;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.metamodel.EntityType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.proto1.domain.Language;
import org.proto1.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

public class JacksonTest {
	@Autowired
	LanguageRepository langrep;

	protected final Log logger = LogFactory.getLog(getClass());
	ObjectMapper mapper = new ObjectMapper();

	static EntityManagerFactory emf;
	static EntityManager em;

	@Test
	@Ignore
	public void test() throws JsonGenerationException, JsonMappingException,
			IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<Language> langlist = (List<Language>) langrep.findAll();
		mapper.writeValue(new File("e:\\!!!\\lang.json"), langlist);
	}
	
	@BeforeClass public static void setUp() {
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", "sa");
		properties.put("javax.persistence.jdbc.password", "");
		emf = Persistence.createEntityManagerFactory("proto1", properties);
		em = emf.createEntityManager();
		
	}

	@Test
	@Ignore
	public void testEntityList() throws JsonGenerationException, JsonMappingException, IOException {


		for(EntityType<?> et : emf.getMetamodel().getEntities()) {
			logger.debug(et.getName());
			
			
			Query q = em.createQuery("from " + et.getName());
			List<?> entlist = q.getResultList();
			mapper.writeValue(new File("e:\\!!!\\" + et.getName()+ ".json"), entlist);
		}
	}
	
	@Test
	public void testParameterJSON() throws JsonGenerationException, JsonMappingException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		Query q = em.createQuery("from ParameterName");
		List<?> entlist = q.getResultList();
		mapper.writerWithDefaultPrettyPrinter().writeValue(new File("e:\\!!!\\ParameterName.json"), entlist);
	
	}

}

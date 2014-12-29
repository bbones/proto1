package protofront;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dozer.Mapper;
import org.junit.Test;
import org.proto1.domain.Language;
import org.proto1.dto.LanguageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:/META-INF/domain.xml" })
public class DozerConfigTest extends AbstractJUnit4SpringContextTests {
	@Autowired
	Language english, russian, ukrainian;

	@Test
	public void test() {
		Mapper mapper = new org.dozer.DozerBeanMapper();
		List<Language> languageList = Arrays.asList(english, russian, ukrainian);
		List<LanguageDTO> langlist = new ArrayList<LanguageDTO>();
		mapper.map(languageList, langlist);
		assertEquals(languageList.size(), langlist.size());
	}

}
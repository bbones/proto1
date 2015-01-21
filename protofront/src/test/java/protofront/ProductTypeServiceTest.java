package protofront;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:/META-INF/domain.xml", "/WEB-INF/applicationContext.xml" })
public class ProductTypeServiceTest  extends AbstractTransactionalJUnit4SpringContextTests {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

package org.proto1.protofront;

import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.proto1.domain.party.EnterpriseName;
import org.proto1.dto.EnterpriseNameDTO;
import org.proto1.dtotools.DTODecode;
import org.proto1.dtotools.DTOMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/META-INF/domain.xml", "classpath:/META-INF/product.xml","classpath:/META-INF/applicationContext.xml"})

public class DTOAnnotationTest {
	protected final Log logger = LogFactory.getLog(getClass());

	EnterpriseNameDTO dto = new EnterpriseNameDTO();
	
	@Autowired
	BeanFactory beanFactory;
	
	@Autowired
	DTOMapper admb;

	@Before
	public void setUp() {

		dto.setEnterpriseId(45L);
		dto.setEnterpriseName("ISD");
		dto.setLanguageId(1L);
		dto.setVersion(1);
		dto.setEnterpriseNameId(1L);
		
	}
	@Test
	public void testSource() {
		Method[] methods = EnterpriseNameDTO.class.getDeclaredMethods();
		for (Method method : methods ) {
			for (Annotation annotation :method.getAnnotationsByType(DTODecode.class) ) {
				logger.debug("Source method->" + method.getName());
				logger.debug("Source annotation->" + annotation.toString());
			}
		}
		
	}
	
	@Test
	public void testDesination() {
		Method[] methods = EnterpriseName.class.getMethods();
		for (Method method : methods ) {
			logger.debug("Dest method->" + method.getName());
		}
		
	}

	
	@Test
	public void testAccessServiceByName() throws ClassNotFoundException {
		
		Class<?> clazz = Class.forName("org.proto1.services.LanguageService");
		Object object = beanFactory.getBean(clazz);
		logger.debug(object.getClass().getCanonicalName());
	}
	
	@Test
	public void decodeTest() throws InstantiationException, IllegalAccessException, 
			SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, BeansException, ClassNotFoundException {
		for(int i = 0; i < 100; i++) {
			EnterpriseName enterpriseName = admb.decode(dto, EnterpriseName.class);
			assertNotNull(enterpriseName);
			assertEquals(dto.getEnterpriseNameId(), enterpriseName.getId());
			assertEquals(dto.getEnterpriseId(), enterpriseName.getEnterprise().getId());
			assertEquals(dto.getLanguageId(), enterpriseName.getLanguage().getId());
		}
	}

}

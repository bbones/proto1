package protofront;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.proto1.domain.product.ProductType;
import org.proto1.domain.product.ProductTypeName;
import org.proto1.domain.utility.LocalizedStringConstant;
import org.proto1.services.MasterDataService;
import org.proto1.services.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(locations = { "classpath:/META-INF/domain.xml", "classpath:/META-INF/product.xml", "classpath:applicationContext.xml" })
public class ProductTypeServiceTest  extends AbstractTransactionalJUnit4SpringContextTests {
	@Autowired
	ProductTypeService pds;
	
	@Autowired
	MasterDataService mds;

	@Autowired
	ProductType ironProduct;
	
	@Autowired
	ProductTypeName 
		ironProductTypeNameEnglish, ironProductTypeNameRussian;

	@Test
	
	public void test() {
		ProductType pt = new ProductType();
		ProductType parent = pds.getNodeById(1L);
		pt.setParentType(parent);
		// pt = pds.save(pt);
		for (LocalizedStringConstant name : mds.getRequiredLocalizedStringList("productType")) {
			ProductTypeName pdn = new ProductTypeName();
			pdn.setLanguage(name.getLanguage());
			pdn.setProductType(pt);
			pdn.setName(name.getText());
			pt.getProductTypeNames().add(pdn);
		}
		pt = pds.save(pt);
	}

	@Test
	@Ignore
	public void testSave() {
		pds.save(ironProduct);
	}
}

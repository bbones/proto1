/**
 * EnterpriseMapperBean.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created May 18, 2015
 */
package org.proto1.mapper;

import org.proto1.domain.party.Enterprise;
import org.proto1.dto.EnterpriseDTO;
import org.springframework.stereotype.Service;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@Service
public class EnterpriseMapperBean implements EnterpriseMapper {

	/* (non-Javadoc)
	 * @see org.proto1.mapper.EnterpriseMapper#map(org.proto1.domain.party.Enterprise)
	 */
	@Override
	public EnterpriseDTO map(Enterprise enterprise) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.proto1.mapper.EnterpriseMapper#map(org.proto1.dto.EnterpriseDTO)
	 */
	@Override
	public Enterprise map(EnterpriseDTO entepriseDTO) {
		// TODO Auto-generated method stub
		return null;
	}

}

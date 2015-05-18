/**
 * EnterpriseMapper.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created May 18, 2015
 */
package org.proto1.mapper;

import org.proto1.domain.party.Enterprise;
import org.proto1.dto.EnterpriseDTO;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface EnterpriseMapper {
	
	EnterpriseDTO map(Enterprise enterprise);
	Enterprise map(EnterpriseDTO entepriseDTO);

}

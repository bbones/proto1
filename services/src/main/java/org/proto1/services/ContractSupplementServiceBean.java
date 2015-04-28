/**
 * ContractSupplementServiceBean.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 28, 2015
 */
package org.proto1.services;

import org.springframework.stereotype.Service;

import org.proto1.domain.ContractSupplement;
import org.proto1.repository.ContractSupplementRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */

@Service
public class ContractSupplementServiceBean implements ContractSupplementService {

	@Autowired
	ContractSupplementRepository contractSupplementRepository;
	
	/* (non-Javadoc)
	 * @see org.proto1.services.ContractSupplentService#get(long)
	 */
	public ContractSupplement get(long id) {
		return contractSupplementRepository.findOne(id);
	}

}

/**
 * PurchaseOrderDTO.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 20, 2015
 */
package org.proto1.dto.order;


/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public class PurchaseOrderDTO extends OrderDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1075550355225583741L;
	
	private Long contractSupplementId;

	public Long getContractSupplementId() {
		return contractSupplementId;
	}

	public void setContractSupplementId(Long contractSupplementId) {
		this.contractSupplementId = contractSupplementId;
	}

}

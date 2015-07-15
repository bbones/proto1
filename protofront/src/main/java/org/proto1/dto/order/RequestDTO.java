/**
 * RequestDTO.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 3, 2015
 */
package org.proto1.dto.order;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public class RequestDTO extends OrderDTO {

	private static final long serialVersionUID = 3696315588089558839L;
	
	private Long orgUnitId;

	public Long getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(Long orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

}

/** Rsk 08.07.2015 */
package org.proto1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Railway extends AbstractEntity {
	@Column(name = "RAILWAY_CODE", unique = true, nullable = false)
	private Integer railwayCode;

	public Integer getRailwayCode() {
		return railwayCode;
	}

	public void setRailwayCode(Integer railwayCode) {
		this.railwayCode = railwayCode;
	}
}

/** Rsk 08.07.2015 */
package org.proto1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RAILWAY_STATION")
public class RailwayStation extends AbstractEntity {
	@Column(name = "RAILWAY_STATION_CODE", nullable = false, length = 10)
	private String railwayStationCode;

	@ManyToOne(optional = false)
	private Railway railway;
	
	public Railway getRailway() {
		return railway;
	}

	public void setRailway(Railway railway) {
		this.railway = railway;
	}

	public String getRailwayStationCode() {
		return railwayStationCode;
	}

	public void setRailwayStationCode(String railwayStationCode) {
		this.railwayStationCode = railwayStationCode;
	}

}

package org.proto1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "RAILWAY_STATION_NAME", uniqueConstraints = @UniqueConstraint(columnNames = {
		"RAILWAY_STATION_ID", "LANGUAGE_ID" }))
public class RailwayStationName extends AbstractEntity {
	@ManyToOne(optional = false)
	@JoinColumn(name="RAILWAY_STATION_ID")
	private RailwayStation railwayStation;

	@ManyToOne(optional = false)
	private Language language;

	@Column(nullable = false, length = 60)
	private String name;

	public RailwayStation getRailwayStation() {
		return railwayStation;
	}

	public void setRailwayStation(RailwayStation railwayStation) {
		this.railwayStation = railwayStation;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

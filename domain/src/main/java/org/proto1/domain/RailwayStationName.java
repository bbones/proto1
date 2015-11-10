/*******************************************************************************
 * Copyright (C) 2015   Serhiy Romaniuk 
 *
 * mail:rsk@isd.com.ua
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * GNU v2 license text in root directory of project
 *******************************************************************************/
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

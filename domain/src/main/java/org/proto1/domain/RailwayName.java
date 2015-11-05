/*******************************************************************************
 * Copyright (C) 2015  Valentin Pogrebinsky
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
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
/** Rsk 08.07.2015 */
package org.proto1.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "RAILWAY_NAME", uniqueConstraints = @UniqueConstraint(columnNames = {
		"RAILWAY_ID", "LANGUAGE_ID" }))
public class RailwayName extends AbstractEntity {
	@ManyToOne(optional = false)
	private Railway railway;

	@ManyToOne(optional = false)
	private Language language;

	@Column(name = "SHORT_NAME", nullable = false, length = 15)
	private String shortName;
	
	@Column(name = "FULL_NAME", nullable = false, length = 80)
	private String fullName;
	
	public Railway getRailway() {
		return railway;
	}
	public void setRailway(Railway railway) {
		this.railway = railway;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

}

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

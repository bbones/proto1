/**
 * CountryName.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created May 13, 2015
 */
package org.proto1.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@Entity
public class CountryName extends AbstractEntity {
	@ManyToOne
	private Country country;
	
	@ManyToOne
	private Language language;
	private String name;
	
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
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

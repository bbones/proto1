package org.proto1.dto;

public class RailwayNameDTO extends DTO {

	private static final long serialVersionUID = 3291938368354934836L;
	
	private Long id;
	private Long railwayId;
	private Long languageId;
	private String shortName;
	private String fullName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getRailwayId() {
		return railwayId;
	}
	public void setRailwayId(Long railwayId) {
		this.railwayId = railwayId;
	}
	
	public Long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Long languageId) {
		this.languageId = languageId;
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

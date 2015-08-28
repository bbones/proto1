package org.proto1.dto;

public class RailwayDTO extends DTO {

	private static final long serialVersionUID = 7105736056541961922L;

	private Long id;
	private Integer railwayCode;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Integer getRailwayCode() {
		return railwayCode;
	}
	public void setRailwayCode(Integer railwayCode) {
		this.railwayCode = railwayCode;
	}

}

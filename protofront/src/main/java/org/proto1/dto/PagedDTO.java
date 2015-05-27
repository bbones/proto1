package org.proto1.dto;

import java.io.Serializable;
import java.util.List;

public class PagedDTO<T> implements Serializable {

	private static final long serialVersionUID = -2706198765024759618L;
	
	private Long total;
	
	private List<T> rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public PagedDTO(Long total, List<T> rows){
		this.total = total;
		this.rows = rows;
	}
}

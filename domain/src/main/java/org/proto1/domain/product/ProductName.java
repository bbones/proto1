package org.proto1.domain.product;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.proto1.domain.Language;

@Entity
@Table(name="PRODUCT_NAME_OBJ")
public class ProductName implements Serializable {
	
	private static final long serialVersionUID = 2044881780858323509L;

	@Id
	@ManyToOne
	private Product product;

	@Id
	@ManyToOne
	private Language language;
	
	private String name;
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
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

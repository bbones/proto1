package org.proto1.domain.product;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.DimensionUnit;

@Entity
public class Receipt extends AbstractEntity {
	private String name;
	
	@ManyToOne
	private DimensionUnit dimensionUnit;
	
	@ManyToOne
	private Product product;
	
	@OneToMany
	private List<ReceiptItem> ingredients;
	
	@OneToMany
	private List<ReceiptItem> byProducts;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<ReceiptItem> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<ReceiptItem> ingredients) {
		this.ingredients = ingredients;
	}

	public List<ReceiptItem> getByProducts() {
		return byProducts;
	}

	public void setByProducts(List<ReceiptItem> byProducts) {
		this.byProducts = byProducts;
	}

	public DimensionUnit getDimensionUnit() {
		return dimensionUnit;
	}

	public void setDimensionUnit(DimensionUnit dimensionUnit) {
		this.dimensionUnit = dimensionUnit;
	}

	
}

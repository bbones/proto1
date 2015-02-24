package org.proto1.domain.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.DimensionUnit;
import org.proto1.domain.Document;

@Entity
public class Receipt extends Document {
	
	@ManyToOne
	private DimensionUnit dimensionUnit;
	
	@ManyToOne
	private Product product;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="receipt_ingredients")

	private List<ReceiptItem> ingredients;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="receipt_byproducts")
	private List<ReceiptItem> byProducts;

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

package org.proto1.domain.product;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import org.proto1.domain.AbstractEntity;

@Entity
@NamedEntityGraph(name = "Product.productNames", attributeNodes = @NamedAttributeNode("productNames"))
public class Product extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 5899772053294304505L;

	@ManyToOne
	private ProductType productType;
	
	@OneToMany
	@JoinColumn(name="PRODUCT_ID")
	private List<ProductName> productNames;
	
	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public List<ProductName> getProductNames() {
		return productNames;
	}

	public void setProductNames(List<ProductName> productNames) {
		this.productNames = productNames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((productNames == null) ? 0 : productNames.hashCode());
		result = prime * result
				+ ((productType == null) ? 0 : productType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productNames == null) {
			if (other.productNames != null)
				return false;
		} else if (!productNames.equals(other.productNames))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		return true;
	}
	
	
}

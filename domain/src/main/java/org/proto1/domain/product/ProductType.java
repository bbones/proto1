package org.proto1.domain.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;

import org.proto1.domain.AbstractEntity;

@Entity
@NamedEntityGraph(name = "ProductType.productTypeNames", attributeNodes = @NamedAttributeNode("productTypeNames"))
public class ProductType extends AbstractEntity {
	@ManyToOne
	private ProductType parentType;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="PRODUCT_TYPE_ID",nullable=true)
	private List<ProductTypeName> productTypeNames = new ArrayList<ProductTypeName>();

	public ProductType getParentType() {
		return parentType;
	}

	public void setParentType(ProductType parentType) {
		this.parentType = parentType;
	}

	public List<ProductTypeName> getProductTypeNames() {
		return productTypeNames;
	}

	public void setProductTypeNames(List<ProductTypeName> productTypeNames) {
		this.productTypeNames = productTypeNames;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((parentType == null) ? 0 : parentType.hashCode());
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
		ProductType other = (ProductType) obj;
		if (parentType == null) {
			if (other.parentType != null)
				return false;
		} else if (!parentType.equals(other.parentType))
			return false;
		return true;
	}
	
}

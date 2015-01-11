package org.proto1.domain.product;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
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
	
	@OneToMany
	private List<ProductTypeName> productTypeNames;

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
	
	
}

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
	
}

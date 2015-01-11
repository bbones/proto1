package org.proto1.domain.product;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import org.proto1.domain.AbstractEntity;
import org.proto1.domain.Identified;
import org.proto1.domain.Language;

@Entity
@NamedEntityGraph(name = "Product.productNames", attributeNodes = @NamedAttributeNode("productNames"))
public class Product extends AbstractEntity implements Serializable {
	@ManyToOne
	private ProductType productType;
	
	@ElementCollection
	@CollectionTable(name="PRODUCT_NAME")
	private Map<Language, String> productNames = new HashMap<Language, String>();
	
	public String getName(Language language) {
		return productNames.get(language);
	}

	public void setName(Language language, String name) {
		productNames.put(language, name);
	}

	public Map<Language, String> getProductNames() {
		return productNames;
	}

	public void setProductNames(Map<Language, String> productNames) {
		this.productNames = productNames;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	
	
}

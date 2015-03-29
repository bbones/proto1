/**
 * NamedParameterValue.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 27, 2015
 */
package org.proto1.domain.valueprovider;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.proto1.domain.AbstractEntity;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@Entity
@Table(name="NAMED_PARAMETER", schema="VALUEPROVIDER")
public class NamedParameterValue extends AbstractEntity{
	
	String value;
	
	@ManyToOne
	@JoinColumn(name="PARENT_VALUE_ID")
	NamedParameterValue parentValue;

}

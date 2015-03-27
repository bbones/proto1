/**
 * NamedParameterValue.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 27, 2015
 */
package org.proto1.domain.utility;

import javax.persistence.Entity;

import org.proto1.domain.AbstractEntity;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
@Entity
public class NamedParameterValue extends AbstractEntity{
	
	String value;
	
	NamedParameterValue parentValue;

}

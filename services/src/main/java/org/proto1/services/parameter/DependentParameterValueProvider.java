/**
 * @author bbones
 */
package org.proto1.services.parameter;

/**
 * @author bbones
 *
 */
public interface DependentParameterValueProvider extends ParameterValueProvider {
	void setMasterObject(Object master);
}

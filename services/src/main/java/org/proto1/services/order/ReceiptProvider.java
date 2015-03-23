/**
 * ReceiptProvider.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Mar 23, 2015
 */
package org.proto1.services.order;

import org.proto1.domain.product.Product;
import org.proto1.domain.product.Receipt;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface ReceiptProvider {
	
	Receipt getDefaultReceipt(Product product);

}

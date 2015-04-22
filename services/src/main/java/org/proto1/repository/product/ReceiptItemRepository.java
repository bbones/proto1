/**
 * ReceiptItemRepository.java
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 * Created Apr 22, 2015
 */
package org.proto1.repository.product;

import org.proto1.domain.product.ReceiptItem;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Valentin Pogrebinsky (pva@isd.com.ua)
 *
 */
public interface ReceiptItemRepository extends CrudRepository<ReceiptItem, Long> {

}

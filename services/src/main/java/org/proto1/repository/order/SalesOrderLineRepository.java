package org.proto1.repository.order;

import org.proto1.domain.order.OrderLine;
import org.springframework.data.repository.CrudRepository;

public interface SalesOrderLineRepository extends CrudRepository<OrderLine, Long> {

}

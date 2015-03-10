package org.proto1.repository.order;

import org.proto1.domain.order.BaseOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseOrderRepository<T extends BaseOrder> extends CrudRepository<T, Long> {

}

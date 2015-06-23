package org.proto1.mapper;

import org.proto1.domain.order.Request;

public class LongRequestConverter extends LongBaseOrderConverter<Request> {

	public LongRequestConverter() {
		super(Long.class, Request.class);
	}

}

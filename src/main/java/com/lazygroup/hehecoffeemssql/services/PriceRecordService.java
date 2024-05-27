package com.lazygroup.hehecoffeemssql.services;

import org.springframework.stereotype.Service;

import com.lazygroup.hehecoffeemssql.dtos.PriceRecordDto;

@Service("priceRecordService")
public interface PriceRecordService {
	PriceRecordDto findByProductId(Long productId);
}

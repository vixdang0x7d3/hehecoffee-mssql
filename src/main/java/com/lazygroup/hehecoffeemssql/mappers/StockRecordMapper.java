package com.lazygroup.hehecoffeemssql.mappers;

import com.lazygroup.hehecoffeemssql.dtos.StockRecordDto;
import com.lazygroup.hehecoffeemssql.models.StockRecord;

public class StockRecordMapper {
	public static StockRecordDto mapToDto(StockRecord stockRecord) {
		StockRecordDto dto = StockRecordDto.builder()
				.productId(stockRecord.getProductId())
				.amount(stockRecord.getAmount())
				.updatedOn(stockRecord.getUpdatedOn())
				.updatedByStaff(stockRecord.getUpdatedByStaff())
				.product(stockRecord.getProduct())
				.build();
		return dto;
	}
}

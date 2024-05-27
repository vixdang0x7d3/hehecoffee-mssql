package com.lazygroup.hehecoffeemssql.mappers;

import com.lazygroup.hehecoffeemssql.dtos.PriceRecordDto;
import com.lazygroup.hehecoffeemssql.models.PriceRecord;

public class PriceRecordMapper {
	public static PriceRecordDto mapToDto(PriceRecord priceRecord) {
		PriceRecordDto dto = PriceRecordDto.builder()
				.productId(priceRecord.getProductId())
				.price(priceRecord.getPrice())
				.discount(priceRecord.getDiscount())
				.updatedOn(priceRecord.getUpdatedOn())
				.updatedByStaff(priceRecord.getUpdatedByStaff())
				.product(priceRecord.getProduct())
				.build();
		return dto;
	}
}

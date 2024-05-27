package com.lazygroup.hehecoffeemssql.mappers;

import com.lazygroup.hehecoffeemssql.dtos.ProductDto;
import com.lazygroup.hehecoffeemssql.models.Product;

public class ProductMapper {

	public static ProductDto mapToDto(Product product) {
		ProductDto dto = ProductDto.builder()
				.id(product.getId())
				.category(product.getCategory())
				.name(product.getName())
				.createdOn(product.getCreatedOn())
				.updatedOn(product.getUpdatedOn())
				.description(product.getDescription())
				.isActive(product.getIsActive())
				.imageUrl(product.getImageUrl())
				.priceRecord(PriceRecordMapper.mapToDto(product.getPriceRecord()))
				.stockRecord(StockRecordMapper.mapToDto(product.getStockRecord()))
				.orderItems(product.getOrderItems())
				.supplyItems(product.getSupplyItems())
				.build();
		return dto;
	}
}

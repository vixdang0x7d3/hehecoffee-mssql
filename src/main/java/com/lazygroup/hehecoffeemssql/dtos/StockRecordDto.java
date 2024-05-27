package com.lazygroup.hehecoffeemssql.dtos;

import java.time.LocalDateTime;

import com.lazygroup.hehecoffeemssql.models.Product;
import com.lazygroup.hehecoffeemssql.models.Staff;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockRecordDto {
	private Long productId;
	private Long amount;
	private LocalDateTime updatedOn;
	private Staff updatedByStaff;
	private Product product;
}

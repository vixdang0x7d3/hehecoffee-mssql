package com.lazygroup.hehecoffeemssql.dtos;

import java.time.LocalDateTime;

import com.lazygroup.hehecoffeemssql.models.Product;
import com.lazygroup.hehecoffeemssql.models.Staff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PriceRecordDto {
	private Long productId;
	private Double price;
	private Double discount;
	private LocalDateTime updatedOn;
	private Staff updatedByStaff;
	private Product product;
}

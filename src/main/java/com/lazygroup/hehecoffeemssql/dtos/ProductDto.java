package com.lazygroup.hehecoffeemssql.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.lazygroup.hehecoffeemssql.models.Category;
import com.lazygroup.hehecoffeemssql.models.OrderItem;
import com.lazygroup.hehecoffeemssql.models.PriceRecord;
import com.lazygroup.hehecoffeemssql.models.StockRecord;
import com.lazygroup.hehecoffeemssql.models.SupplyItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
	private Long id;

	private Category category;

	private String name;

	private LocalDateTime createdOn;

	private LocalDateTime updatedOn;

	private String description;

	private Boolean isActive;

	private String imageUrl;

	private PriceRecordDto priceRecord;

	private StockRecordDto stockRecord;

	private List<OrderItem> orderItems;

	private List<SupplyItem> supplyItems;
}

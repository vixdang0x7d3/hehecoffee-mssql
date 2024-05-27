package com.lazygroup.hehecoffeemssql.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.lazygroup.hehecoffeemssql.models.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
	private String id;
	private String name;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	private List<ProductDto> products;
}

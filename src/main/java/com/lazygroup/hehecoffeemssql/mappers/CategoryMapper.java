package com.lazygroup.hehecoffeemssql.mappers;

import java.util.stream.Collectors;

import com.lazygroup.hehecoffeemssql.dtos.CategoryDto;
import com.lazygroup.hehecoffeemssql.models.Category;

import lombok.Data;

@Data
public class CategoryMapper {
	public static CategoryDto mapToDto(Category category) {
		CategoryDto dto = CategoryDto.builder()
				.id(category.getId())
				.name(category.getName())
				.createdOn(category.getCreatedOn())
				.updatedOn(category.getUpdatedOn())
				.products(category.getProducts().stream()
						.map((product) -> ProductMapper.mapToDto(product))
						.collect(Collectors.toList()))
				.build();
		return dto;
	}
}

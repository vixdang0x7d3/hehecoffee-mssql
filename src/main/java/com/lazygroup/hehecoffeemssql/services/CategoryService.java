package com.lazygroup.hehecoffeemssql.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lazygroup.hehecoffeemssql.dtos.CategoryDto;

@Service
public interface CategoryService {
	CategoryDto findById(String id);

	CategoryDto findByName(String name);

	List<CategoryDto> findAll();
}

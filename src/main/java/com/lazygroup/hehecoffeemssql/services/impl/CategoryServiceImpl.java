package com.lazygroup.hehecoffeemssql.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazygroup.hehecoffeemssql.dtos.CategoryDto;
import com.lazygroup.hehecoffeemssql.mappers.CategoryMapper;
import com.lazygroup.hehecoffeemssql.models.Category;
import com.lazygroup.hehecoffeemssql.repositories.CategoryRepository;
import com.lazygroup.hehecoffeemssql.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepo;

	@Autowired
	CategoryServiceImpl(CategoryRepository categoryRepo) {
		this.categoryRepo = categoryRepo;
	}

	@Override
	public CategoryDto findById(String id) {
		Category category = categoryRepo.findById(id).orElseThrow();

		return CategoryMapper.mapToDto(category);
	}

	@Override
	public CategoryDto findByName(String name) {
		Category category = categoryRepo.findByName(name).orElseThrow();

		return CategoryMapper.mapToDto(category);
	}

	@Override
	public List<CategoryDto> findAll() {
		List<Category> categories = categoryRepo.findAll();

		return categories.stream().map((category) -> CategoryMapper.mapToDto(category))
				.collect(Collectors.toList());
	}
}

package com.lazygroup.hehecoffeemssql.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lazygroup.hehecoffeemssql.dtos.CategoryDto;
import com.lazygroup.hehecoffeemssql.dtos.ProductDto;
import com.lazygroup.hehecoffeemssql.services.CategoryService;
import com.lazygroup.hehecoffeemssql.services.ProductService;

@Controller
public class AdminController {

	private ProductService productService;
	private CategoryService categoryService;

	@Autowired
	AdminController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@GetMapping("/admin/product/list")
	public ModelAndView listProducts() {
		ModelAndView modelAndView = new ModelAndView("/admin/product-list");

		List<ProductDto> products = productService.findAll();
		List<CategoryDto> categories = categoryService.findAll();

		modelAndView.addObject("products", products);
		modelAndView.addObject("categories", categories);

		return modelAndView;
	}
}

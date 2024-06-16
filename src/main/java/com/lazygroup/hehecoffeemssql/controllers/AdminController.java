package com.lazygroup.hehecoffeemssql.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lazygroup.hehecoffeemssql.configs.SecurityUtil;
import com.lazygroup.hehecoffeemssql.dtos.CategoryDto;
import com.lazygroup.hehecoffeemssql.dtos.ProductDto;
import com.lazygroup.hehecoffeemssql.models.Staff;
import com.lazygroup.hehecoffeemssql.services.CategoryService;
import com.lazygroup.hehecoffeemssql.services.ProductService;
import com.lazygroup.hehecoffeemssql.services.StaffService;

@Controller
public class AdminController {

	private ProductService productService;
	private CategoryService categoryService;
	private StaffService staffService;

	@Autowired
	AdminController(ProductService productService, CategoryService categoryService, StaffService staffService) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.staffService = staffService;
	}

	@GetMapping("/admin/product/list")
	public ModelAndView listProducts() {

		Staff currentSessionUser = staffService.findByLoginName(SecurityUtil.currentSessionUsername()).get();

		ModelAndView modelAndView = new ModelAndView("/admin/product-list");

		List<ProductDto> products = productService.findAll();
		List<CategoryDto> categories = categoryService.findAll();

		modelAndView.addObject("currentSessionUser", currentSessionUser);
		modelAndView.addObject("products", products);
		modelAndView.addObject("categories", categories);

		return modelAndView;
	}
}

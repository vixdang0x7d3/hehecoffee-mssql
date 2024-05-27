package com.lazygroup.hehecoffeemssql.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lazygroup.hehecoffeemssql.dtos.CategoryDto;
import com.lazygroup.hehecoffeemssql.dtos.ProductDto;
import com.lazygroup.hehecoffeemssql.mappers.ProductMapper;
import com.lazygroup.hehecoffeemssql.services.CategoryService;
import com.lazygroup.hehecoffeemssql.services.ProductService;

@Controller
public class ProductController {

	private ProductService productService;
	private CategoryService categoryService;

	@Autowired
	ProductController(ProductService productService, CategoryService categoryService) {
		this.productService = productService;
		this.categoryService = categoryService;
	}

	@GetMapping("/product/list")
	public String listProducts(@RequestParam Optional<String> categoryId, ModelMap modelMap) {

		List<CategoryDto> categories = categoryService.findAll();

		modelMap.addAttribute("categories", categories);

		categoryId.ifPresentOrElse(
				(catId) -> {
					modelMap.addAttribute("products",
							categoryService.findById(catId)
									.getProducts());
				},
				() -> {
					modelMap.addAttribute("products", productService.findAll());
				});

		return "product-list";
	}

	@GetMapping("/product/{productId}/detail")
	public String showDetail(@PathVariable Long productId, ModelMap modelMap) {

		ProductDto product = productService.findById(productId);

		modelMap.addAttribute("product", product);

		return "product-detail";
	}
}

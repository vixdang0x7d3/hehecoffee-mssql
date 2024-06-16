package com.lazygroup.hehecoffeemssql.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lazygroup.hehecoffeemssql.dtos.CategoryDto;
import com.lazygroup.hehecoffeemssql.dtos.ProductDto;
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
	public String listProducts(ModelMap modelMap,
			// pageIndex on URL start from 1 while Spring Data JPA Pagination starts from 0,
			// offsetting is performed in service
			@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize,
			@RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "ASC") String sortDirection,
			@RequestParam Optional<String> categoryId,
			@RequestParam Optional<String> q) {

		// handling search query, early return
		if (q.isPresent()) {

			Page<ProductDto> results = productService.findByName(pageNo, pageSize, sortBy, sortDirection,
					q.get());

			modelMap.addAttribute("queryString", q.get());
			modelMap.addAttribute("products", results);

			int totalPages = results.getTotalPages();
			if (totalPages > 0) {
				List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed()
						.collect(Collectors.toList());
				modelMap.addAttribute("pageNumbers", pageNumbers);
			}

			return "/customer/product-list";
		}

		// handling sorting, categories, pagination

		List<CategoryDto> categories = categoryService.findAll();

		modelMap.addAttribute("categories", categories);

		categoryId.ifPresentOrElse(
				(catId) -> {
					Page<ProductDto> results = productService.findByCategoryId(pageNo, pageSize,
							sortBy, sortDirection, catId);

					modelMap.addAttribute("products", results);

					int totalPages = results.getTotalPages();
					if (totalPages > 0) {
						List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed()
								.collect(Collectors.toList());
						modelMap.addAttribute("pageNumbers", pageNumbers);
					}
				},
				() -> {
					Page<ProductDto> results = productService.findAll(pageNo, pageSize, sortBy,
							sortDirection);

					modelMap.addAttribute("products", results);

					int totalPages = results.getTotalPages();
					if (totalPages > 0) {
						List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed()
								.collect(Collectors.toList());
						modelMap.addAttribute("pageNumbers", pageNumbers);
					}
				});

		return "/customer/product-list";
	}

	@GetMapping("/product/{productId}/detail")
	public String showDetail(@PathVariable Long productId, ModelMap modelMap) {

		ProductDto product = productService.findById(productId);

		modelMap.addAttribute("product", product);

		return "/customer/product-detail";
	}
}

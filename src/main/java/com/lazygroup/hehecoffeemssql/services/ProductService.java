package com.lazygroup.hehecoffeemssql.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lazygroup.hehecoffeemssql.dtos.ProductDto;
import com.lazygroup.hehecoffeemssql.models.Product;

@Service
public interface ProductService {

	List<ProductDto> findAll();

	Page<ProductDto> findAll(int pageNo, int pageSize, String sortBy, String sortDirection);

	Page<ProductDto> findByCategoryId(int pageNo, int pageSize, String sortBy, String sortDirection,
			String categoryId);

	ProductDto findById(long productId);

	Product save(Product product);

	void update(ProductDto productDto);

	Page<ProductDto> findByName(int pageNo, int pageSize, String sortBy, String sortDirection,
			String nameQuery);
}

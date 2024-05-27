package com.lazygroup.hehecoffeemssql.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lazygroup.hehecoffeemssql.dtos.ProductDto;
import com.lazygroup.hehecoffeemssql.models.Product;

@Service
public interface ProductService {

	List<ProductDto> findAll();

	ProductDto findById(long productId);

	Product save(Product product);

	void update(ProductDto productDto);
}

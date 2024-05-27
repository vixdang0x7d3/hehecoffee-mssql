
package com.lazygroup.hehecoffeemssql.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazygroup.hehecoffeemssql.dtos.ProductDto;
import com.lazygroup.hehecoffeemssql.mappers.ProductMapper;
import com.lazygroup.hehecoffeemssql.models.Product;
import com.lazygroup.hehecoffeemssql.repositories.ProductRepository;
import com.lazygroup.hehecoffeemssql.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	ProductRepository productRepo;

	@Autowired
	ProductServiceImpl(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@Override
	public List<ProductDto> findAll() {
		List<Product> products = productRepo.findAll();

		return products.stream().map((club) -> ProductMapper.mapToDto(club)).collect(Collectors.toList());
	}

	@Override
	public ProductDto findById(long productId) {
		Product product = productRepo.findById(productId).orElseThrow();

		return ProductMapper.mapToDto(product);
	}

	@Override
	public Product save(Product product) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'save'");
	}

	@Override
	public void update(ProductDto productDto) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}
}

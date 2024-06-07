
package com.lazygroup.hehecoffeemssql.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

		return products.stream().map((product) -> ProductMapper.mapToDto(product)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	@Override
	public Page<ProductDto> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {

		if (sortBy.equals("price")) {

			Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

			var direction = sortDirection.equals("DESC") ? 1 : 0;
			List<Product> products = productRepo.findAllSortByPrice(direction);

			int start = (int) pageable.getOffset();
			System.out.println(pageable.getOffset());

			int end = Math.min((start + pageable.getPageSize()), products.size());

			List<Product> pageContent = products.subList(start, end);

			return new PageImpl<>(pageContent, pageable, products.size())
					.map(ProductMapper::mapToDto);
		}

		Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		Page<ProductDto> products = productRepo.findAll(pageable).map(ProductMapper::mapToDto);

		return products;
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

	@Transactional(readOnly = true)
	@Override
	public Page<ProductDto> findByCategoryId(int pageNo, int pageSize, String sortBy, String sortDirection,
			String categoryId) {

		if (sortBy.equals("price")) {

			var direction = sortDirection.equals("DESC") ? 1 : 0;
			List<Product> products = productRepo.findByXSortByName("category_id", categoryId, direction);

			Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
			int start = (int) pageable.getOffset();
			int end = Math.min((start + pageable.getPageSize()), products.size());

			List<Product> pageContent = products.subList(start, end);

			return new PageImpl<>(pageContent, pageable, products.size()).map(ProductMapper::mapToDto);
		}

		Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		Page<ProductDto> products = productRepo.findByCategoryId(pageable, categoryId)
				.map(ProductMapper::mapToDto);

		return products;
	}

	@Override
	public Page<ProductDto> findByName(int pageNo, int pageSize, String sortBy, String sortDirection,
			String nameQuery) {

		Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		Page<ProductDto> products = productRepo.findByName(pageable, nameQuery).map(ProductMapper::mapToDto);

		return products;
	}
}

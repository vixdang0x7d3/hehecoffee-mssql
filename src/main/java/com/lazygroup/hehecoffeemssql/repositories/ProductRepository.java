package com.lazygroup.hehecoffeemssql.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lazygroup.hehecoffeemssql.models.Category;
import com.lazygroup.hehecoffeemssql.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByName(String name);

	List<Product> findAllByCategory(Category category);
}

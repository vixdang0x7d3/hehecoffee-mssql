package com.lazygroup.hehecoffeemssql.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lazygroup.hehecoffeemssql.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(value = "SELECT * FROM products", nativeQuery = true)
	Page<Product> findAll(Pageable pageable);

	@Query(value = "SELECT * FROM products WHERE name LIKE CONCAT('%', :query, '%')", nativeQuery = true)
	Page<Product> findByName(Pageable pageable, String query);

	@Query(value = "SELECT * FROM products WHERE category_id = :categoryId", nativeQuery = true)
	Page<Product> findByCategoryId(Pageable pageable, String categoryId);

	@Transactional(readOnly = true)
	@Procedure(procedureName = "dbo.sp_findAllProductsSortByPrice")
	List<Product> findAllSortByPrice(@Param("direction") int sortDirection);

	@Transactional(readOnly = true)
	@Procedure(procedureName = "dbo.sp_findProductsByXSortByPrice")
	List<Product> findByXSortByName(@Param("X") String x, @Param("VAL") String val,
			@Param("direction") int sortDirection);
}

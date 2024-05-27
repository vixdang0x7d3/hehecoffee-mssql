package com.lazygroup.hehecoffeemssql.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lazygroup.hehecoffeemssql.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
	Optional<Category> findByName(String name);
}

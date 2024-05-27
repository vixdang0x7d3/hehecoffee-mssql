package com.lazygroup.hehecoffeemssql.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.lazygroup.hehecoffeemssql.models.Category;
import com.lazygroup.hehecoffeemssql.models.PriceRecord;
import com.lazygroup.hehecoffeemssql.models.Product;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringJUnitConfig
@Sql("classpath:dbscripts/init.sql")
public class ProductRepositoryTests {

	@Autowired
	CategoryRepository categoryRepo;

	@Autowired
	ProductRepository productRepo;

	@Autowired
	PriceRecordRepository priceRecordRepo;

	@Container
	private static final MSSQLServerContainer<?> database = new MSSQLServerContainer<>(
			DockerImageName.parse("mcr.microsoft.com/mssql/server:2017-latest"))
			.acceptLicense();

	@DynamicPropertySource
	static void setDatsourceProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", database::getJdbcUrl);
		registry.add("spring.datasource.username", database::getUsername);
		registry.add("spring.datasource.password", database::getPassword);
	}

	@Test
	void loadContexts() {
		assertNotNull(productRepo);
		assertNotNull(categoryRepo);
	}

	@Test
	@Sql(scripts = { "classpath:dbscripts/populate_test_1.sql" })
	void findAllWorksFine() {
		List<Product> products = productRepo.findAll();

		products.forEach((product) -> System.out.println(product.getName()));

		assertEquals(3, products.size());
	}

	@Test
	@Sql(scripts = { "classpath:dbscripts/populate_test_1.sql" })
	void saveProductWorksFine() {

		Category c02 = categoryRepo.findById("C02").orElseThrow();

		Product newProd = Product.builder()
				.category(c02)
				.name("Ca phe so 3")
				.description("This description is from springboot")
				.isActive(true)
				.build();

		Product savedProduct = productRepo.save(newProd);

		assertEquals(3, savedProduct.getId());

		Product fetchFromDB = productRepo.findById(savedProduct.getId()).orElseThrow();

		assertTrue(fetchFromDB.getName().equals(newProd.getName()));
	}

	@Test
	@Sql(scripts = { "classpath:dbscripts/populate_test_2.sql" })
	void findAllByCategoryWorksFine() {

		Category c01 = categoryRepo.findById("C01").orElseThrow();

		List<Product> results = productRepo.findAllByCategory(c01);

		assertEquals(2, results.size());
	}
}

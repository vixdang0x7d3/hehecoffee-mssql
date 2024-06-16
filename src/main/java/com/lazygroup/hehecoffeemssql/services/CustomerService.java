package com.lazygroup.hehecoffeemssql.services;

import java.util.List;
import java.util.Optional;

import com.lazygroup.hehecoffeemssql.dtos.CustomerRegistrationDto;
import com.lazygroup.hehecoffeemssql.models.Customer;

public interface CustomerService {
	public void save(CustomerRegistrationDto customerDto);

	List<Customer> findAll();

	Optional<Customer> findById(Long id);

	Optional<Customer> findByEmail(String email);

	Optional<Customer> findByPhone(String phone);
}

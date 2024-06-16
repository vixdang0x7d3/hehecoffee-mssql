package com.lazygroup.hehecoffeemssql.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazygroup.hehecoffeemssql.dtos.CustomerRegistrationDto;
import com.lazygroup.hehecoffeemssql.models.Customer;
import com.lazygroup.hehecoffeemssql.repositories.CustomerRepository;
import com.lazygroup.hehecoffeemssql.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final CustomerRepository customerRepo;

	@Autowired
	CustomerServiceImpl(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Override
	public void save(CustomerRegistrationDto customerDto) {
		Customer customer = new Customer();

		customer.setId(customerDto.getId());
		customer.setFirstname(customerDto.getFirstname());
		customer.setLastname(customerDto.getLastname());
		customer.setPhone(customerDto.getPhone());
		customer.setEmail(customerDto.getEmail());
		customer.setPasswordHash(customerDto.getPassword());
		customer.setAddress(customerDto.getAddress());
		customer.setProvince(customerDto.getProvince());
		customer.setCity(customerDto.getCity());
		customer.setIsActive(customerDto.getIsActive());

		customerRepo.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerRepo.findAll();
	}

	@Override
	public Optional<Customer> findById(Long id) {
		return customerRepo.findById(id);
	}

	@Override
	public Optional<Customer> findByEmail(String email) {
		return customerRepo.findByEmail(email);
	}

	@Override
	public Optional<Customer> findByPhone(String phone) {
		return customerRepo.findByPhone(phone);
	}

}

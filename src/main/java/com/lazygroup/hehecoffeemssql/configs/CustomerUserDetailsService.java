package com.lazygroup.hehecoffeemssql.configs;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lazygroup.hehecoffeemssql.models.Customer;
import com.lazygroup.hehecoffeemssql.repositories.CustomerRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	private final CustomerRepository customerRepo;

	@Autowired
	CustomerUserDetailsService(CustomerRepository customerRepo) {
		this.customerRepo = customerRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Customer> optionalCustomer = customerRepo.findByEmail(username);

		if (optionalCustomer.isPresent()) {
			Customer customer = optionalCustomer.get();
			User authUser = new User(customer.getEmail(), customer.getPasswordHash(),
					List.of(new SimpleGrantedAuthority("USER")));

			return authUser;
		} else {
			throw new UsernameNotFoundException("Invalid username or password");
		}
	}

}

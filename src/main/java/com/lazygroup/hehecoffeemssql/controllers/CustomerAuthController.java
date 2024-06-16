package com.lazygroup.hehecoffeemssql.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lazygroup.hehecoffeemssql.models.Customer;
import com.lazygroup.hehecoffeemssql.services.CustomerService;

@Controller
public class CustomerAuthController {

	CustomerService customerService;

	@Autowired
	CustomerAuthController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@GetMapping("/login")
	public String loginForm() {
		return "/customer/login";
	}
}

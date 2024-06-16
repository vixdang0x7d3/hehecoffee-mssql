package com.lazygroup.hehecoffeemssql.dtos;

import com.lazygroup.hehecoffeemssql.models.City;
import com.lazygroup.hehecoffeemssql.models.Province;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerRegistrationDto {
	private Long id;

	@NotEmpty
	private String firstname;

	@NotEmpty
	private String lastname;

	@NotEmpty
	private String phone;

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	@NotEmpty
	private String passwordReEnter;

	@NotEmpty
	private String address;

	@NotEmpty
	private City city;

	@NotEmpty
	private Province province;

	private Boolean isActive;

	@AssertTrue(message = "Supplied passwords do not match")
	Boolean passwordsMatch() {
		return password.equals(passwordReEnter);
	}
}

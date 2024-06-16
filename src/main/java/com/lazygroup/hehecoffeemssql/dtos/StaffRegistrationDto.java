package com.lazygroup.hehecoffeemssql.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StaffRegistrationDto {
	private Long id;

	@NotNull
	private Boolean isAdmin;
	@NotEmpty
	private String firstname;
	@NotEmpty
	private String lastname;
	@NotEmpty
	private String phone;
	@NotEmpty
	private String email;
	@NotEmpty
	private String loginName;
	@NotEmpty
	private String password;
	@NotEmpty
	private String passwordReEntered;

	private Boolean isActive;

	@AssertTrue(message = "entered passwords are not matched")
	public Boolean passwordMatches() {
		return (password.equals(passwordReEntered));
	}
}
